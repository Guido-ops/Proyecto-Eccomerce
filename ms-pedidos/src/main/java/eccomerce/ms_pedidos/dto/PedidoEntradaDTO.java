package eccomerce.ms_pedidos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Datos para crear un pedido completo con sus artículos")
public class PedidoEntradaDTO {

    @Schema(description = "ID del usuario que realiza el pedido", example = "10")
    @NotNull(message = "El identificador del usuario es obligatorio")
    private Long usuarioId;

    @Schema(description = "Monto total del pedido", example = "119980.0")
    @NotNull(message = "El monto total del pedido es obligatorio")
    @Positive(message = "El monto total debe ser mayor a cero")
    private Double total;

    @Schema(description = "ID del estado inicial del pedido (opcional, por defecto PENDIENTE)", example = "1", nullable = true)
    private Long estadoId;

    @Schema(description = "Lista de artículos incluidos en el pedido")
    @NotEmpty(message = "El pedido debe contener al menos un articulo")
    @Valid
    private List<DetallePedidoEntradaDTO> articulos = new ArrayList<>();

    public PedidoEntradaDTO() {}

    public Long getUsuarioId() { return usuarioId; }
    public Double getTotal() { return total; }
    public Long getEstadoId() { return estadoId; }
    public List<DetallePedidoEntradaDTO> getArticulos() { return articulos; }

    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public void setTotal(Double total) { this.total = total; }
    public void setEstadoId(Long estadoId) { this.estadoId = estadoId; }
    public void setArticulos(List<DetallePedidoEntradaDTO> articulos) { this.articulos = articulos; }
}
