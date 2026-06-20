package eccomerce.ms_pedidos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Datos completos de un pedido registrado en el sistema")
public class PedidoSalidaDTO {

    @Schema(description = "ID del pedido", example = "5")
    private Long id;

    @Schema(description = "Fecha y hora en que se registró el pedido", example = "2024-01-15T09:00:00")
    private LocalDateTime fechaPedido;

    @Schema(description = "Monto total del pedido", example = "119980.0")
    private Double total;

    @Schema(description = "ID del usuario que realizó el pedido", example = "10")
    private Long usuarioId;

    @Schema(description = "Estado actual del pedido")
    private EstadoPedidoDTO estado;

    @Schema(description = "Lista de artículos del pedido")
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
