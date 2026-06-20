package eccomerce.ms_inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Detalle de un movimiento de inventario registrado")
public class MovimientoInventarioResponseDTO {

    @Schema(description = "ID del movimiento", accessMode = Schema.AccessMode.READ_ONLY, example = "10")
    private Long id;

    @Schema(description = "ID del inventario al que pertenece el movimiento", example = "1")
    private Long inventarioId;

    @Schema(description = "Tipo de movimiento: ENTRADA, SALIDA o AJUSTE", example = "SALIDA")
    private String tipo;

    @Schema(description = "Cantidad de unidades involucradas en el movimiento", example = "5")
    private Integer cantidad;

    @Schema(description = "Fecha y hora del movimiento", example = "2024-01-15T10:30:00")
    private LocalDateTime fecha;

    @Schema(description = "Stock resultante tras el movimiento", example = "75")
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
