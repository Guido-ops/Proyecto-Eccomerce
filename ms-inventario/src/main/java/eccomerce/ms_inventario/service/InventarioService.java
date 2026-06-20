package eccomerce.ms_inventario.service;

import eccomerce.ms_inventario.dto.InventarioCreacionRequestDTO;
import eccomerce.ms_inventario.dto.InventarioMovimientoRequestDTO;
import eccomerce.ms_inventario.dto.InventarioResumenResponseDTO;
import eccomerce.ms_inventario.dto.MovimientoInventarioResponseDTO;
import eccomerce.ms_inventario.dto.ProductoExternoDTO;
import eccomerce.ms_inventario.mapper.InventarioMapper;
import eccomerce.ms_inventario.model.Inventario;
import eccomerce.ms_inventario.model.MovimientoInventario;
import eccomerce.ms_inventario.repository.InventarioRepository;
import eccomerce.ms_inventario.repository.MovimientoInventarioRepository;
import eccomerce.ms_inventario.client.ProductoCliente;
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
    private final ProductoCliente                 productoClient;   // ← NUEVO

    public InventarioService(InventarioRepository inventarioRepository,
                             MovimientoInventarioRepository movimientoRepository,
                             InventarioMapper mapper,
                             ProductoCliente productoClient) {
        this.inventarioRepository = inventarioRepository;
        this.movimientoRepository = movimientoRepository;
        this.mapper               = mapper;
        this.productoClient       = productoClient;
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
 
        // ── Validación contra ms-productos ──────────────────────────────────
        ProductoExternoDTO producto = productoClient.buscarProducto(dto.getProductoId());
 
        if (!producto.isActivo()) {
            throw new IllegalArgumentException(
                "No se puede crear inventario para un producto inactivo: " + producto.getNombre());
        }
 
        log.info("[InventarioService] Producto validado: {} (id={})", producto.getNombre(), producto.getId());
 
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