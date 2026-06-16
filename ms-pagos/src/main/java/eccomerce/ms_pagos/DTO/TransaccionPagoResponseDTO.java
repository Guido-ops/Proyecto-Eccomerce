package eccomerce.ms_pagos.DTO;

import java.time.LocalDateTime;

public class TransaccionPagoResponseDTO {

    private Long id;
    private String codigoRef;
    private String detalle;
    private LocalDateTime fecha;

    // Solo el id del pago — el objeto completo está disponible en GET /pagos/{id}
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
