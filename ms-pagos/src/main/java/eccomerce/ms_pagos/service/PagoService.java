package eccomerce.ms_pagos.service;

import eccomerce.ms_pagos.DTO.MetodoPagoDTO;
import eccomerce.ms_pagos.DTO.PagoRequestDTO;
import eccomerce.ms_pagos.DTO.PagoResponseDTO;
import eccomerce.ms_pagos.DTO.TransaccionPagoResponseDTO;
import eccomerce.ms_pagos.mapper.PagoMapper;
import eccomerce.ms_pagos.model.MetodoPago;
import eccomerce.ms_pagos.model.Pago;
import eccomerce.ms_pagos.model.TransaccionPago;
import eccomerce.ms_pagos.repository.MetodoPagoRepository;
import eccomerce.ms_pagos.repository.PagoRepository;
import eccomerce.ms_pagos.repository.TransaccionPagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    private final PagoRepository            pagoRepository;
    private final MetodoPagoRepository      metodoPagoRepository;
    private final TransaccionPagoRepository transaccionRepository;
    private final PagoMapper                mapper;

    public PagoService(PagoRepository pagoRepository,
                       MetodoPagoRepository metodoPagoRepository,
                       TransaccionPagoRepository transaccionRepository,
                       PagoMapper mapper) {
        this.pagoRepository       = pagoRepository;
        this.metodoPagoRepository = metodoPagoRepository;
        this.transaccionRepository = transaccionRepository;
        this.mapper               = mapper;
    }

    // ══ MÉTODOS DE PAGO ════════════════════════════════════════════════════════

    public List<MetodoPagoDTO> listarMetodos() {
        return metodoPagoRepository.findByActivoTrue()
                .stream()
                .map(mapper::toMetodoPagoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public MetodoPagoDTO crearMetodo(MetodoPagoDTO dto) {
        if (metodoPagoRepository.existsByNombre(dto.getNombre()))
            throw new IllegalArgumentException("Método ya existe: " + dto.getNombre());

        return mapper.toMetodoPagoDTO(metodoPagoRepository.save(mapper.toMetodoPagoEntity(dto)));
    }

    // ══ PAGOS ══════════════════════════════════════════════════════════════════

    public List<PagoResponseDTO> listarTodos() {
        return pagoRepository.findAll()
                .stream()
                .map(mapper::toPagoResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PagoResponseDTO> listarPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId)
                .stream()
                .map(mapper::toPagoResponseDTO)
                .collect(Collectors.toList());
    }

    public PagoResponseDTO buscarPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));
        return mapper.toPagoResponseDTO(pago);
    }

    @Transactional
    public PagoResponseDTO procesarPago(PagoRequestDTO dto) {
        MetodoPago metodo = metodoPagoRepository.findById(dto.getMetodoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado: " + dto.getMetodoId()));

        Pago guardado = pagoRepository.save(mapper.toEntity(dto, metodo));

        TransaccionPago tx = new TransaccionPago();
        tx.setCodigoRef("TXN-" + System.currentTimeMillis());
        tx.setDetalle("Pago iniciado con " + metodo.getNombre());
        tx.setPago(guardado);
        transaccionRepository.save(tx);

        log.info("[PagoService] Pago procesado id={}", guardado.getId());
        return mapper.toPagoResponseDTO(guardado);
    }

    @Transactional
    public PagoResponseDTO actualizarEstado(Long id, String estado) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));

        pago.setEstado(estado);
        Pago actualizado = pagoRepository.save(pago);

        TransaccionPago tx = new TransaccionPago();
        tx.setCodigoRef("TXN-" + System.currentTimeMillis());
        tx.setDetalle("Estado actualizado a: " + estado);
        tx.setPago(actualizado);
        transaccionRepository.save(tx);

        return mapper.toPagoResponseDTO(actualizado);
    }

    // ══ TRANSACCIONES ══════════════════════════════════════════════════════════

    public List<TransaccionPagoResponseDTO> listarTransacciones(Long pagoId) {
        if (!pagoRepository.existsById(pagoId))
            throw new RuntimeException("Pago no encontrado: " + pagoId);

        return transaccionRepository.findByPagoId(pagoId)
                .stream()
                .map(mapper::toTransaccionResponseDTO)
                .collect(Collectors.toList());
    }
}