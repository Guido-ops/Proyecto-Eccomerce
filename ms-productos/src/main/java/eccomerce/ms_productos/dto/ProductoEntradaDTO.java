package eccomerce.ms_productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos para registrar o actualizar un producto en el catálogo")
public class ProductoEntradaDTO {

    @Schema(description = "Nombre del producto", example = "Galaxy S24 Ultra")
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 30, message = "El nombre no puede superar los 30 caracteres")
    private String nombre;

    @Schema(description = "Precio de venta", example = "999990.0")
    @NotNull(message = "El precio del producto es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    @Schema(description = "Cantidad inicial en stock", example = "50")
    @NotNull(message = "La cantidad inicial en stock es obligatoria")
    @PositiveOrZero(message = "El stock inicial no puede ser negativo")
    private Integer stock;

    @Schema(description = "Código SKU único del producto (opcional)", example = "SAM-S24U-256", nullable = true)
    @Size(max = 50, message = "El SKU no puede superar los 50 caracteres")
    private String sku;

    @Schema(description = "Id de la categoría a la que pertenece el producto (debe existir)", example = "1")
    @NotNull(message = "El identificador de la categoria es obligatorio")
    private Long categoriaId;

    @Schema(description = "Id de la marca del producto (opcional, debe existir si se especifica)", example = "2", nullable = true)
    private Long marcaId;

    public ProductoEntradaDTO() {}

    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    public Integer getStock() { return stock; }
    public String getSku() { return sku; }
    public Long getCategoriaId() { return categoriaId; }
    public Long getMarcaId() { return marcaId; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public void setStock(Integer stock) { this.stock = stock; }
    public void setSku(String sku) { this.sku = sku; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public void setMarcaId(Long marcaId) { this.marcaId = marcaId; }
}