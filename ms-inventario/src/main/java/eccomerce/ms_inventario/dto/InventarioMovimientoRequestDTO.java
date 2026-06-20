package eccomerce.ms_inventario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos para registrar un movimiento de inventario")
public class InventarioMovimientoRequestDTO {

    @Schema(description = "Tipo de movimiento: ENTRADA, SALIDA o AJUSTE", example = "ENTRADA")
    @NotBlank(message = "El tipo de movimiento es obligatorio")
    private String tipo;

    @Schema(description = "Cantidad de unidades del movimiento", example = "20")
    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @Schema(description = "Descripción o motivo del movimiento (opcional)", example = "Reposición proveedor", nullable = true)
    private String motivo;

    public InventarioMovimientoRequestDTO() {}

    public String getTipo() { return tipo; }
    public Integer getCantidad() { return cantidad; }
    public String getMotivo() { return motivo; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
