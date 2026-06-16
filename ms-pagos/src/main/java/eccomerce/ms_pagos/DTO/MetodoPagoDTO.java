package eccomerce.ms_pagos.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MetodoPagoDTO {

    private Long id;

    @NotBlank(message = "El nombre del método de pago es obligatorio")
    @Size(max = 50)
    private String nombre;

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
