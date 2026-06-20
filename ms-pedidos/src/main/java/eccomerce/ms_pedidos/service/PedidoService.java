package eccomerce.ms_pedidos.service;

import eccomerce.ms_pedidos.dto.*;
import eccomerce.ms_pedidos.mapper.*;
import eccomerce.ms_pedidos.model.*;
import eccomerce.ms_pedidos.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private EstadoPedidoRepository estadoRepository;
    @Autowired private DetallePedidoRepository detalleRepository;

    // ── EstadoPedido ──────────────────────────────────────────────────────────
    public List<EstadoPedidoDTO> listarEstados() {
        return estadoRepository.findAll().stream()
            .map(EstadoPedidoMapper::toDTO)
            .toList();
    }

    public Optional<EstadoPedidoDTO> buscarEstadoPorId(Long id) {
        return estadoRepository.findById(id).map(EstadoPedidoMapper::toDTO);
    }

    @Transactional
    public EstadoPedidoDTO crearEstado(EstadoPedidoDTO dto) {
        EstadoPedido entity = EstadoPedidoMapper.toEntity(dto);
        entity.setId(null);
        return EstadoPedidoMapper.toDTO(estadoRepository.save(entity));
    }

    // ── Pedido ────────────────────────────────────────────────────────────────
    public List<PedidoSalidaDTO> listarTodos() {
        return pedidoRepository.findAll().stream()
            .map(PedidoMapper::toDTO)
            .toList();
    }

    public Optional<PedidoSalidaDTO> buscarPorId(Long id) {
        return pedidoRepository.findById(id).map(PedidoMapper::toDTO);
    }

    public List<PedidoSalidaDTO> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId).stream()
            .map(PedidoMapper::toDTO)
            .toList();
    }

    /**
     * Crea un pedido completo (cabecera + articulos) en una sola operacion
     * a partir de un PedidoEntradaDTO. Si el dto no trae estadoId, el sistema
     * usa el estado PENDIENTE. El total se recalcula a partir de los articulos
     * para evitar manipulaciones.
     */
    @Transactional
    public PedidoSalidaDTO crear(PedidoEntradaDTO dto) {
        log.info("[PedidoService] Creando pedido para usuario {}", dto.getUsuarioId());

        Pedido pedido = PedidoMapper.toEntity(dto);

        // Resolver el estado: por id o por defecto PENDIENTE
        EstadoPedido estado;
        if (dto.getEstadoId() != null) {
            estado = estadoRepository.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado: " + dto.getEstadoId()));
        } else {
            estado = estadoRepository.findByNombre("PENDIENTE")
                .orElseThrow(() -> new RuntimeException("Crea el estado PENDIENTE primero"));
        }
        pedido.setEstado(estado);

        // Recalcular total como medida de seguridad
        double totalCalculado = pedido.getDetalles().stream()
            .mapToDouble(d -> d.getPrecioUnit() * d.getCantidad())
            .sum();
        pedido.setTotal(totalCalculado);

        // Enlazar cada detalle con el pedido para que la cascada los persista
        for (DetallePedido d : pedido.getDetalles()) {
            d.setPedido(pedido);
        }

        Pedido guardado = pedidoRepository.save(pedido);
        log.info("[PedidoService] Pedido creado id={}", guardado.getId());
        return PedidoMapper.toDTO(guardado);
    }

    @Transactional
    public PedidoSalidaDTO cambiarEstado(Long pedidoId, Long estadoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + pedidoId));
        EstadoPedido estado = estadoRepository.findById(estadoId)
            .orElseThrow(() -> new RuntimeException("Estado no encontrado: " + estadoId));
        pedido.setEstado(estado);
        return PedidoMapper.toDTO(pedidoRepository.save(pedido));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!pedidoRepository.existsById(id))
            throw new RuntimeException("Pedido no encontrado: " + id);
        pedidoRepository.deleteById(id);
    }

    public List<DetallePedidoSalidaDTO> listarDetalles(Long pedidoId) {
        return detalleRepository.findByPedidoId(pedidoId).stream()
            .map(DetallePedidoMapper::toDTO)
            .toList();
    }
}
