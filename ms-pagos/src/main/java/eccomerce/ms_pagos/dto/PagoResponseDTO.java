package eccomerce.ms_pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un pago registrado en el sistema")
public class PagoResponseDTO {

    @Schema(description = "ID del pago", example = "7")
    private Long id;

    @Schema(description = "Monto del pago", example = "59990.0")
    private Double monto;

    @Schema(description = "ID del pedido asociado al pago", example = "3")
    private Long pedidoId;

    @Schema(description = "Estado actual del pago", example = "PENDIENTE")
    private String estado;

    @Schema(description = "Nombre del método de pago utilizado", example = "Tarjeta de crédito")
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
