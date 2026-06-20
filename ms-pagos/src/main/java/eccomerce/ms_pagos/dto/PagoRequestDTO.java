package eccomerce.ms_pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos para procesar un nuevo pago")
public class PagoRequestDTO {

    @Schema(description = "Monto a pagar", example = "59990.0")
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    @Schema(description = "ID del pedido al que corresponde el pago", example = "3")
    @NotNull(message = "El id del pedido es obligatorio")
    private Long pedidoId;

    @Schema(description = "ID del método de pago seleccionado", example = "1")
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
