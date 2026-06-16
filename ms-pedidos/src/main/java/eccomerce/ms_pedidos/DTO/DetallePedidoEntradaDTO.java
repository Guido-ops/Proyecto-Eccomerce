package eccomerce.ms_pedidos.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO auxiliar para PedidoEntradaDTO.
 * Representa un articulo enviado por el cliente al confirmar un pedido.
 * No expone el identificador del pedido (lo asigna el sistema).
 */
public class DetallePedidoEntradaDTO {

    @NotNull(message = "El identificador del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a cero")
    private Double precioUnit;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @Size(max = 200, message = "La descripcion no puede superar los 200 caracteres")
    private String descripcion;

    public DetallePedidoEntradaDTO() {}

    public Long getProductoId() { return productoId; }
    public Double getPrecioUnit() { return precioUnit; }
    public Integer getCantidad() { return cantidad; }
    public String getDescripcion() { return descripcion; }

    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public void setPrecioUnit(Double precioUnit) { this.precioUnit = precioUnit; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
