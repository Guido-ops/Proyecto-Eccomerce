package eccomerce.ms_pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Transacción generada durante el procesamiento de un pago")
public class TransaccionPagoResponseDTO {

    @Schema(description = "ID de la transacción", example = "15")
    private Long id;

    @Schema(description = "Código de referencia externo de la transacción", example = "TXN-20240115-001")
    private String codigoRef;

    @Schema(description = "Descripción del resultado de la transacción", example = "Pago autorizado por la entidad emisora")
    private String detalle;

    @Schema(description = "Fecha y hora de la transacción", example = "2024-01-15T10:30:00")
    private LocalDateTime fecha;

    @Schema(description = "ID del pago al que pertenece esta transacción", example = "7")
    private Long pagoId;

    public TransaccionPagoResponseDTO() {}

    public TransaccionPagoResponseDTO(Long id, String codigoRef, String detalle,
                                       LocalDateTime fecha, Long pagoId) {
        this.id        = id;
        this.codigoRef = codigoRef;
        this.detalle   = detalle;
        this.fecha     = fecha;
        this.pagoId    = pagoId;
    }

    public Long getId()              { return id; }
    public String getCodigoRef()     { return codigoRef; }
    public String getDetalle()       { return detalle; }
    public LocalDateTime getFecha()  { return fecha; }
    public Long getPagoId()          { return pagoId; }

    public void setId(Long id)                   { this.id = id; }
    public void setCodigoRef(String codigoRef)   { this.codigoRef = codigoRef; }
    public void setDetalle(String detalle)        { this.detalle = detalle; }
    public void setFecha(LocalDateTime fecha)     { this.fecha = fecha; }
    public void setPagoId(Long pagoId)            { this.pagoId = pagoId; }
}
