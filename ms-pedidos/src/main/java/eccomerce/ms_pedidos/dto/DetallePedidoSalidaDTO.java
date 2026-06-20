package eccomerce.ms_pedidos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Artículo de un pedido (salida)")
public class DetallePedidoSalidaDTO {

    @Schema(description = "ID del detalle", example = "12")
    private Long id;

    @Schema(description = "ID del producto", example = "3")
    private Long productoId;

    @Schema(description = "Precio unitario del producto", example = "59990.0")
    private Double precioUnit;

    @Schema(description = "Cantidad de unidades", example = "2")
    private Integer cantidad;

    @Schema(description = "Descripción adicional del artículo (opcional)", example = "Edición especial", nullable = true)
    private String descripcion;

    public DetallePedidoSalidaDTO() {}

    public DetallePedidoSalidaDTO(Long id, Long productoId, Double precioUnit,
                                  Integer cantidad, String descripcion) {
        this.id = id;
        this.productoId = productoId;
        this.precioUnit = precioUnit;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public Long getProductoId() { return productoId; }
    public Double getPrecioUnit() { return precioUnit; }
    public Integer getCantidad() { return cantidad; }
    public String getDescripcion() { return descripcion; }

    public void setId(Long id) { this.id = id; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public void setPrecioUnit(Double precioUnit) { this.precioUnit = precioUnit; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
