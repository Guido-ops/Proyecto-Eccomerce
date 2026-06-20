package eccomerce.ms_inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resumen del estado actual del inventario de un producto")
public class InventarioResumenResponseDTO {

    @Schema(description = "ID del inventario", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(description = "ID del producto asociado", example = "5")
    private Long productoId;

    @Schema(description = "Stock disponible actualmente", example = "80")
    private Integer stockActual;

    @Schema(description = "Límite mínimo de stock configurado", example = "10")
    private Integer stockMinimo;

    @Schema(description = "Indica si el stock actual está por debajo del mínimo", example = "false")
    private Boolean stockBajo;

    public InventarioResumenResponseDTO() {}

    public InventarioResumenResponseDTO(Long id, Long productoId, Integer stockActual, Integer stockMinimo, Boolean stockBajo) {
        this.id = id;
        this.productoId = productoId;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockBajo = stockBajo;
    }

    public Long getId() { return id; }
    public Long getProductoId() { return productoId; }
    public Integer getStockActual() { return stockActual; }
    public Integer getStockMinimo() { return stockMinimo; }
    public Boolean getStockBajo() { return stockBajo; }

    public void setId(Long id) { this.id = id; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
    public void setStockBajo(Boolean stockBajo) { this.stockBajo = stockBajo; }
}
