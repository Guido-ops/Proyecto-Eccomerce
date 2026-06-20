package eccomerce.ms_productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Imagen asociada a un producto")
public class ImagenProductoDTO {

    @Schema(description = "Id generado por el sistema", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "URL pública de la imagen", example = "https://cdn.shopconnect.com/productos/galaxy-s24.jpg")
    private String url;

    @Schema(description = "Orden de visualización de la imagen", example = "1")
    private Integer orden;

    @Schema(description = "Indica si es la imagen principal del producto", example = "true")
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