package eccomerce.ms_pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Método de pago disponible en el sistema")
public class MetodoPagoDTO {

    @Schema(description = "ID del método de pago, generado por el sistema", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(description = "Nombre del método de pago", example = "Tarjeta de crédito")
    @NotBlank(message = "El nombre del método de pago es obligatorio")
    @Size(max = 50)
    private String nombre;

    @Schema(description = "Indica si el método está disponible para pagos", example = "true")
    private Boolean activo;

    public MetodoPagoDTO() {}

    public MetodoPagoDTO(Long id, String nombre, Boolean activo) {
        this.id     = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Long getId()        { return id; }
    public String getNombre()  { return nombre; }
    public Boolean getActivo() { return activo; }

    public void setId(Long id)            { this.id = id; }
    public void setNombre(String nombre)  { this.nombre = nombre; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
