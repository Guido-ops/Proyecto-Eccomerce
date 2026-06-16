package eccomerce.ms_pagos.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PagoRequestDTO {

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    @NotNull(message = "El id del pedido es obligatorio")
    private Long pedidoId;

    @NotNull(message = "El id del método de pago es obligatorio")
    private Long metodoId;

    public PagoRequestDTO() {}

    public PagoRequestDTO(Double monto, Long pedidoId, Long metodoId) {
        this.monto    = monto;
        this.pedidoId = pedidoId;
        this.metodoId = metodoId;
    }

    public Double getMonto()    { return monto; }
    public Long getPedidoId()   { return pedidoId; }
    public Long getMetodoId()   { return metodoId; }

    public void setMonto(Double monto)      { this.monto = monto; }
    public void setPedidoId(Long pedidoId)  { this.pedidoId = pedidoId; }
    public void setMetodoId(Long metodoId)  { this.metodoId = metodoId; }
}
