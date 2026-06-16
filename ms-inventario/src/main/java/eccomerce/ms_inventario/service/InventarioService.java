package eccomerce.ms_inventario.service;

import eccomerce.ms_inventario.DTO.InventarioCreacionRequestDTO;
import eccomerce.ms_inventario.DTO.InventarioMovimientoRequestDTO;
import eccomerce.ms_inventario.DTO.InventarioResumenResponseDTO;
import eccomerce.ms_inventario.DTO.MovimientoInventarioResponseDTO;
import eccomerce.ms_inventario.mapper.InventarioMapper;
import eccomerce.ms_inventario.model.Inventario;
import eccomerce.ms_inventario.model.MovimientoInventario;
import eccomerce.ms_inventario.repository.InventarioRepository;
import eccomerce.ms_inventario.repository.MovimientoInventarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    private static final Logger log = LoggerFactory.getLogger(InventarioService.class);

    private final InventarioRepository           inventarioRepository;
    private final MovimientoInventarioRepository movimientoRepository;
    private final InventarioMapper               mapper;

    public InventarioService(InventarioRepository inventarioRepository,
                             MovimientoInventarioRepository movimientoRepository,
                             InventarioMapper mapper) {
        this.inventarioRepository = inventarioRepository;
        this.movimientoRepository = movimientoRepository;
        this.mapper               = mapper;
    }

    // ══ INVENTARIO ════════════════════════════════════════════════════════════

    public List<InventarioResumenResponseDTO> listarTodos() {
        return inventarioRepository.findAll()
                .stream()
                .map(mapper::toResumenResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<InventarioResumenResponseDTO> buscarPorId(Long id) {
        return inventarioRepository.findById(id)
                .map(mapper::toResumenResponseDTO);
    }

    public Optional<InventarioResumenResponseDTO> buscarPorProducto(Long productoId) {
        return inventarioRepository.findByProductoId(productoId)
                .map(mapper::toResumenResponseDTO);
    }

    @Transactional
    public InventarioResumenResponseDTO crear(InventarioCreacionRequestDTO dto) {
        if (inventarioRepository.existsByProductoId(dto.getProductoId()))
            throw new IllegalArgumentException("Ya existe inventario para el producto: " + dto.getProductoId());

        Inventario guardado = inventarioRepository.save(mapper.toEntity(dto));
        return mapper.toResumenResponseDTO(guardado);
    }

    // ══ MOVIMIENTOS ═══════════════════════════════════════════════════════════

    @Transactional
    public MovimientoInventarioResponseDTO registrarMovimiento(Long inventarioId,
                                                               InventarioMovimientoRequestDTO dto) {
        Inventario inventario = inventarioRepository.findById(inventarioId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado: " + inventarioId));

        String tipo     = dto.getTipo().toUpperCase();
        Integer cantidad = dto.getCantidad();

        switch (tipo) {
            case "ENTRADA":
                inventario.setStockActual(inventario.getStockActual() + cantidad);
                break;
            case "SALIDA":
                if (inventario.getStockActual() < cantidad)
                    throw new IllegalArgumentException("Stock insuficiente. Stock actual: " + inventario.getStockActual());
                inventario.setStockActual(inventario.getStockActual() - cantidad);
                break;
            case "AJUSTE":
                inventario.setStockActual(cantidad);
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido. Usar: ENTRADA, SALIDA o AJUSTE");
        }

        inventarioRepository.save(inventario);

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);
        movimiento.setInventario(inventario);

        MovimientoInventario guardado = movimientoRepository.save(movimiento);

        log.info("[InventarioService] Movimiento {} en inventario {}: stock={}",
                tipo, inventarioId, inventario.getStockActual());

        return mapper.toMovimientoResponseDTO(guardado, inventario.getStockActual());
    }

    public List<MovimientoInventarioResponseDTO> listarMovimientos(Long inventarioId) {
        return movimientoRepository.findByInventarioIdOrderByFechaDesc(inventarioId)
                .stream()
                .map(m -> mapper.toMovimientoResponseDTO(m, m.getInventario().getStockActual()))
                .collect(Collectors.toList());
    }
}