package eccomerce.ms_inventario.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InventarioMovimientoRequestDTO {
    @NotBlank(message = "El tipo de movimiento es obligatorio")
    private String tipo;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    private String motivo;

    public InventarioMovimientoRequestDTO() {}

    public String getTipo() { return tipo; }
    public Integer getCantidad() { return cantidad; }
    public String getMotivo() { return motivo; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
