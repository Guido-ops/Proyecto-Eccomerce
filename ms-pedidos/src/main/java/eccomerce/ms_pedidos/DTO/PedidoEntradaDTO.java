package eccomerce.ms_pedidos.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO 10 - PedidoEntradaDTO (Entrada)
 * Transporta toda la informacion necesaria para registrar un pedido
 * completo en una sola operacion: los datos del pedido y la lista de
 * articulos incluidos. Permite que el sistema cree el pedido y todos
 * sus articulos de forma atomica.
 */
public class PedidoEntradaDTO {

    @NotNull(message = "El identificador del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El monto total del pedido es obligatorio")
    @Positive(message = "El monto total debe ser mayor a cero")
    private Double total;

    /** Opcional: por defecto el sistema asigna el estado Pendiente. */
    private Long estadoId;

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
