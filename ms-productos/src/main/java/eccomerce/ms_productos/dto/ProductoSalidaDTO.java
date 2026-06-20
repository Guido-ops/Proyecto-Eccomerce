package eccomerce.ms_productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Ficha completa de un producto lista para mostrarse en la tienda")
public class ProductoSalidaDTO {

    @Schema(description = "Id generado por el sistema", example = "10")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Galaxy S24 Ultra")
    private String nombre;

    @Schema(description = "Precio de venta", example = "999990.0")
    private Double precio;

    @Schema(description = "Unidades disponibles en stock", example = "50")
    private Integer stock;

    @Schema(description = "Código SKU único", example = "SAM-S24U-256")
    private String sku;

    @Schema(description = "Indica si el producto está activo en el catálogo", example = "true")
    private boolean activo;

    @Schema(description = "Categoría a la que pertenece el producto")
    private CategoriaDTO categoria;

    @Schema(description = "Marca del producto (opcional, puede ser nula)", nullable = true)
    private MarcaDTO marca;

    @Schema(description = "Lista de imágenes del producto")
    private List<ImagenProductoDTO> imagenes = new ArrayList<>();

    public ProductoSalidaDTO() {}

    public ProductoSalidaDTO(Long id, String nombre, Double precio, Integer stock, String sku,
                             boolean activo, CategoriaDTO categoria, MarcaDTO marca,
                             List<ImagenProductoDTO> imagenes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.sku = sku;
        this.activo = activo;
        this.categoria = categoria;
        this.marca = marca;
        this.imagenes = imagenes != null ? imagenes : new ArrayList<>();
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    public Integer getStock() { return stock; }
    public String getSku() { return sku; }
    public boolean isActivo() { return activo; }
    public CategoriaDTO getCategoria() { return categoria; }
    public MarcaDTO getMarca() { return marca; }
    public List<ImagenProductoDTO> getImagenes() { return imagenes; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setSku(String sku) { this.sku = sku; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public void setCategoria(CategoriaDTO categoria) { this.categoria = categoria; }
    public void setMarca(MarcaDTO marca) { this.marca = marca; }
    public void setImagenes(List<ImagenProductoDTO> imagenes) { this.imagenes = imagenes; }
}