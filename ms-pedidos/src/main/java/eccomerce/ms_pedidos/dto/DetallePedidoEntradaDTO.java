package eccomerce.ms_pedidos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Artículo incluido en un pedido (entrada)")
public class DetallePedidoEntradaDTO {

    @Schema(description = "ID del producto", example = "3")
    @NotNull(message = "El identificador del producto es obligatorio")
    private Long productoId;

    @Schema(description = "Precio unitario del producto al momento del pedido", example = "59990.0")
    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a cero")
    private Double precioUnit;

    @Schema(description = "Cantidad de unidades del producto", example = "2")
    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @Schema(description = "Descripción adicional del artículo (opcional)", example = "Edición especial", nullable = true)
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
