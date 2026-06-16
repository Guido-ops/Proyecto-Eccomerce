package eccomerce.ms_pedidos.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO 11 - PedidoSalidaDTO (Salida)
 * Muestra el estado completo de un pedido: cuando se hizo, cuanto vale,
 * en que etapa esta y que articulos incluye. Estructura plana, sin
 * referencias circulares con DetallePedido ni EstadoPedido.
 */
public class PedidoSalidaDTO {

    private Long id;
    private LocalDateTime fechaPedido;
    private Double total;
    private Long usuarioId;
    private EstadoPedidoDTO estado;
    private List<DetallePedidoSalidaDTO> articulos = new ArrayList<>();

    public PedidoSalidaDTO() {}

    public PedidoSalidaDTO(Long id, LocalDateTime fechaPedido, Double total, Long usuarioId,
                           EstadoPedidoDTO estado, List<DetallePedidoSalidaDTO> articulos) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.usuarioId = usuarioId;
        this.estado = estado;
        this.articulos = articulos != null ? articulos : new ArrayList<>();
    }

    public Long getId() { return id; }
    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public Double getTotal() { return total; }
    public Long getUsuarioId() { return usuarioId; }
    public EstadoPedidoDTO getEstado() { return estado; }
    public List<DetallePedidoSalidaDTO> getArticulos() { return articulos; }

    public void setId(Long id) { this.id = id; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }
    public void setTotal(Double total) { this.total = total; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public void setEstado(EstadoPedidoDTO estado) { this.estado = estado; }
    public void setArticulos(List<DetallePedidoSalidaDTO> articulos) { this.articulos = articulos; }
}
