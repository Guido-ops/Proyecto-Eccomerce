package eccomerce.ms_inventario.DTO;

public class InventarioResumenResponseDTO {
    private Long id;
    private Long productoId;
    private Integer stockActual;
    private Integer stockMinimo;
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
