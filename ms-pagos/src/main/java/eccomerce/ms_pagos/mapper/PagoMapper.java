package eccomerce.ms_pagos.mapper;

import eccomerce.ms_pagos.DTO.MetodoPagoDTO;
import eccomerce.ms_pagos.DTO.PagoRequestDTO;
import eccomerce.ms_pagos.DTO.PagoResponseDTO;
import eccomerce.ms_pagos.DTO.TransaccionPagoResponseDTO;
import eccomerce.ms_pagos.model.MetodoPago;
import eccomerce.ms_pagos.model.Pago;
import eccomerce.ms_pagos.model.TransaccionPago;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    // ── MetodoPago → MetodoPagoDTO ────────────────────────────────────────────

    public MetodoPagoDTO toMetodoPagoDTO(MetodoPago m) {
        if (m == null) return null;
        return new MetodoPagoDTO(m.getId(), m.getNombre(), m.isActivo());
    }

    // ── Pago → PagoResponseDTO ────────────────────────────────────────────────

    public PagoResponseDTO toPagoResponseDTO(Pago p) {
        String nombreMetodo = p.getMetodoPago() != null ? p.getMetodoPago().getNombre() : null;
        return new PagoResponseDTO(
            p.getId(),
            p.getMonto(),
            p.getPedidoId(),
            p.getEstado(),
            nombreMetodo
        );
    }

    // ── TransaccionPago → TransaccionPagoResponseDTO ──────────────────────────

    public TransaccionPagoResponseDTO toTransaccionResponseDTO(TransaccionPago t) {
        Long pagoId = t.getPago() != null ? t.getPago().getId() : null;
        return new TransaccionPagoResponseDTO(
            t.getId(),
            t.getCodigoRef(),
            t.getDetalle(),
            t.getFecha(),
            pagoId
        );
    }

    // ── PagoRequestDTO → Pago (entidad nueva) ─────────────────────────────────

    public Pago toEntity(PagoRequestDTO dto, MetodoPago metodo) {
        Pago pago = new Pago();
        pago.setMonto(dto.getMonto());
        pago.setPedidoId(dto.getPedidoId());
        pago.setMetodoPago(metodo);
        pago.setEstado("PENDIENTE");
        return pago;
    }

    // ── MetodoPagoDTO → MetodoPago (entidad nueva) ────────────────────────────

    public MetodoPago toMetodoPagoEntity(MetodoPagoDTO dto) {
        MetodoPago metodo = new MetodoPago();
        metodo.setNombre(dto.getNombre());
        metodo.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        return metodo;
    }
}
