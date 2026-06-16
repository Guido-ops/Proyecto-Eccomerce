package eccomerce.ms_pagos.DTO;


public class PagoResponseDTO {

    private Long id;
    private Double monto;
    private Long pedidoId;
    private String estado;

    // MetodoPago → solo nombre (no el objeto completo ni su lista de pagos)
    private String metodoPagoNombre;

    public PagoResponseDTO() {}

    public PagoResponseDTO(Long id, Double monto, Long pedidoId,
                            String estado, String metodoPagoNombre) {
        this.id               = id;
        this.monto            = monto;
        this.pedidoId         = pedidoId;
        this.estado           = estado;
        this.metodoPagoNombre = metodoPagoNombre;
    }

    public Long getId()                 { return id; }
    public Double getMonto()            { return monto; }
    public Long getPedidoId()           { return pedidoId; }
    public String getEstado()           { return estado; }
    public String getMetodoPagoNombre() { return metodoPagoNombre; }

    public void setId(Long id)                              { this.id = id; }
    public void setMonto(Double monto)                      { this.monto = monto; }
    public void setPedidoId(Long pedidoId)                  { this.pedidoId = pedidoId; }
    public void setEstado(String estado)                    { this.estado = estado; }
    public void setMetodoPagoNombre(String metodoPagoNombre){ this.metodoPagoNombre = metodoPagoNombre; }
}
