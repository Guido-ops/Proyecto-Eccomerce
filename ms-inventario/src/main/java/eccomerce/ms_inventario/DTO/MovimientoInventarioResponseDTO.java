package eccomerce.ms_inventario.DTO;

import java.time.LocalDateTime;

public class MovimientoInventarioResponseDTO {
    private Long id;
    private Long inventarioId;
    private String tipo;
    private Integer cantidad;
    private LocalDateTime fecha;
    private Integer stockActual;

    public MovimientoInventarioResponseDTO() {}

    public MovimientoInventarioResponseDTO(Long id, Long inventarioId, String tipo, Integer cantidad, LocalDateTime fecha, Integer stockActual) {
        this.id = id;
        this.inventarioId = inventarioId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.stockActual = stockActual;
    }

    public Long getId() { return id; }
    public Long getInventarioId() { return inventarioId; }
    public String getTipo() { return tipo; }
    public Integer getCantidad() { return cantidad; }
    public LocalDateTime getFecha() { return fecha; }
    public Integer getStockActual() { return stockActual; }

    public void setId(Long id) { this.id = id; }
    public void setInventarioId(Long inventarioId) { this.inventarioId = inventarioId; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
}
