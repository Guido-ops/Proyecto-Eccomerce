package eccomerce.ms_pagos.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
/**
 * Transacción generada por un pago. Tabla: transaccion_pago
 * Relación JPA: TransaccionPago ──@ManyToOne──► Pago
 */
@Entity @Table(name = "transaccion_pago")
public class TransaccionPago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "codigo_ref", length = 100) private String codigoRef;
    @Column(length = 300) private String detalle;
    @Column(nullable = false) private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pago_id", nullable = false)
    @JsonIgnore private Pago pago;

    public TransaccionPago() {}

    //Getters
    public Long getId() { return id; } 
    public String getCodigoRef() { return codigoRef; } 
    public String getDetalle() { return detalle; } 
    public LocalDateTime getFecha() { return fecha; } 
    public Pago getPago() { return pago; } 

    //Setters
    public void setId(Long id) { this.id = id; }
    public void setDetalle(String d) { this.detalle = d; }
    public void setPago(Pago p) { this.pago = p; }
    public void setFecha(LocalDateTime f) { this.fecha = f; }
    public void setCodigoRef(String c) { this.codigoRef = c; }
}