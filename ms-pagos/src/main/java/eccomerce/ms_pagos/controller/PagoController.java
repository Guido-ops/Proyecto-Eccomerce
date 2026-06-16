package eccomerce.ms_pagos.controller;

import eccomerce.ms_pagos.DTO.MetodoPagoDTO;
import eccomerce.ms_pagos.DTO.PagoRequestDTO;
import eccomerce.ms_pagos.DTO.PagoResponseDTO;
import eccomerce.ms_pagos.DTO.TransaccionPagoResponseDTO;
import eccomerce.ms_pagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // ══ MÉTODOS DE PAGO ════════════════════════════════════════════════════════

    @GetMapping("/metodos")
    public ResponseEntity<List<MetodoPagoDTO>> listarMetodos() {
        return ResponseEntity.ok(pagoService.listarMetodos());
    }

    @PostMapping("/metodos")
    public ResponseEntity<MetodoPagoDTO> crearMetodo(@Valid @RequestBody MetodoPagoDTO DTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.crearMetodo(DTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    // ══ PAGOS ══════════════════════════════════════════════════════════════════

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listar(
            @RequestParam(required = false) Long pedidoId) {
        if (pedidoId != null) return ResponseEntity.ok(pagoService.listarPorPedido(pedidoId));
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PagoResponseDTO> procesarPago(@Valid @RequestBody PagoRequestDTO DTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.procesarPago(DTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PagoResponseDTO> actualizarEstado(@PathVariable Long id,
                                               @RequestParam String estado) {
        try {
            return ResponseEntity.ok(pagoService.actualizarEstado(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ══ TRANSACCIONES ══════════════════════════════════════════════════════════

    @GetMapping("/{id}/transacciones")
    public ResponseEntity<List<TransaccionPagoResponseDTO>> listarTransacciones(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.listarTransacciones(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
