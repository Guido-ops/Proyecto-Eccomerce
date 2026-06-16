package eccomerce.ms_pedidos.DTO;

/**
 * DTO auxiliar para PedidoSalidaDTO.
 * Representa un articulo dentro de un pedido tal como se muestra al cliente.
 * No expone la referencia al objeto Pedido para evitar referencias circulares.
 */
public class DetallePedidoSalidaDTO {

    private Long id;
    private Long productoId;
    private Double precioUnit;
    private Integer cantidad;
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
