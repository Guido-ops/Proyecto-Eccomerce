package eccomerce.ms_inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Datos para crear un nuevo registro de inventario")
public class InventarioCreacionRequestDTO {

    @Schema(description = "ID del producto al que pertenece el inventario", example = "5")
    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @Schema(description = "Stock inicial disponible", example = "100")
    @NotNull(message = "El stock actual es obligatorio")
    @PositiveOrZero(message = "El stock actual no puede ser negativo")
    private Integer stockActual;

    @Schema(description = "Stock mínimo antes de generar alerta de stock bajo", example = "10")
    @NotNull(message = "El stock mínimo es obligatorio")
    @PositiveOrZero(message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;

    public InventarioCreacionRequestDTO() {}

    public Long getProductoId() { return productoId; }
    public Integer getStockActual() { return stockActual; }
    public Integer getStockMinimo() { return stockMinimo; }

    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
}
