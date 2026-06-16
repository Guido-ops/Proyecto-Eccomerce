package eccomerce.ms_pagos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "METODO_PAGO")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "met_pago_seq")
    @SequenceGenerator(name = "met_pago_seq", sequenceName = "MET_PAGO_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Boolean activo = true;

    //Constructor vacio
    public MetodoPago() {}

    //Getters
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public Boolean isActivo() {return activo;}
    
    //Setters
    public void setId(Long id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setActivo(Boolean activo) {this.activo = activo;}
}