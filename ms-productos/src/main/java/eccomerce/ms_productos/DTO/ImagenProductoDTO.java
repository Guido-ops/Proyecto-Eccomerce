package eccomerce.ms_productos.DTO;

/**
 * DTO auxiliar para representar una imagen dentro de la ficha de un producto
 * (ProductoSalidaDTO). Evita exponer la referencia al objeto Producto y
 * por lo tanto las referencias circulares.
 */
public class ImagenProductoDTO {

    private Long id;
    private String url;
    private Integer orden;
    private boolean principal;

    public ImagenProductoDTO() {}

    public ImagenProductoDTO(Long id, String url, Integer orden, boolean principal) {
        this.id = id;
        this.url = url;
        this.orden = orden;
        this.principal = principal;
    }

    public Long getId() { return id; }
    public String getUrl() { return url; }
    public Integer getOrden() { return orden; }
    public boolean isPrincipal() { return principal; }

    public void setId(Long id) { this.id = id; }
    public void setUrl(String url) { this.url = url; }
    public void setOrden(Integer orden) { this.orden = orden; }
    public void setPrincipal(boolean principal) { this.principal = principal; }
}
