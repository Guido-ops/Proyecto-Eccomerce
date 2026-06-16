package eccomerce.ms_pagos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "PAGO")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_seq")
    @SequenceGenerator(name = "pago_seq", sequenceName = "PAGO_SEQ", allocationSize = 1)
    private Long id;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double monto;

    @NotNull
    @Column(name = "PEDIDO_ID", nullable = false)
    private Long pedidoId;

    @Column(length = 20)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private MetodoPago metodoPago;

    //Constructor vacio
    public Pago() {}

    //Getters
    public Long getId() {return id;}
    public Double getMonto() {return monto;}
    public MetodoPago getMetodoPago() {return metodoPago;}
    public Long getPedidoId() {return pedidoId;}
    public String getEstado() {return estado;}
    
    //Setters
    public void setId(Long id) {this.id = id;}
    public void setMonto(Double monto) {this.monto = monto;}
    public void setMetodoPago(MetodoPago metodoPago) {this.metodoPago = metodoPago;}
    public void setPedidoId(Long pedidoId) {this.pedidoId = pedidoId;}
    public void setEstado(String estado) {this.estado = estado;}

}
