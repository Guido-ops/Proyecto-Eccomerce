package com.eccomerce.usuarios.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RolDTO {

    private Long id;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 50)
    private String nombre;

    @Size(max = 200)
    private String descripcion;

    public RolDTO() {}

    public RolDTO(Long id, String nombre, String descripcion) {
        this.id          = id;
        this.nombre      = nombre;
        this.descripcion = descripcion;
    }

    public Long getId()            { return id; }
    public String getNombre()      { return nombre; }
    public String getDescripcion() { return descripcion; }

    public void setId(Long id)                 { this.id = id; }
    public void setNombre(String nombre)       { this.nombre = nombre; }
    public void setDescripcion(String desc)    { this.descripcion = desc; }
}
