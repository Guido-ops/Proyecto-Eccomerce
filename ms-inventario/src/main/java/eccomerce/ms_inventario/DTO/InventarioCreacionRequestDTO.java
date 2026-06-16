package eccomerce.ms_inventario.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class InventarioCreacionRequestDTO {
    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @NotNull(message = "El stock actual es obligatorio")
    @PositiveOrZero(message = "El stock actual no puede ser negativo")
    private Integer stockActual;

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
