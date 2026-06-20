package eccomerce.ms_pagos.controller;

import eccomerce.ms_pagos.dto.ErrorResponseDTO;
import eccomerce.ms_pagos.dto.MetodoPagoDTO;
import eccomerce.ms_pagos.dto.PagoRequestDTO;
import eccomerce.ms_pagos.dto.PagoResponseDTO;
import eccomerce.ms_pagos.dto.TransaccionPagoResponseDTO;
import eccomerce.ms_pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Procesamiento de pagos, métodos de pago y transacciones")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // ══ MÉTODOS DE PAGO ════════════════════════════════════════════════════════

    @Operation(summary = "Listar métodos de pago activos")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de métodos de pago obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = MetodoPagoDTO.class))
        )
    )
    @GetMapping("/metodos")
    public ResponseEntity<List<MetodoPagoDTO>> listarMetodos() {
        return ResponseEntity.ok(pagoService.listarMetodos());
    }

    @Operation(summary = "Crear un nuevo método de pago")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Método creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MetodoPagoDTO.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Ya existe un método con ese nombre",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping("/metodos")
    public ResponseEntity<?> crearMetodo(@Valid @RequestBody MetodoPagoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.crearMetodo(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    // ══ PAGOS ══════════════════════════════════════════════════════════════════

    @Operation(
        summary = "Listar pagos",
        description = "Si se especifica pedidoId, filtra los pagos de ese pedido"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de pagos obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = PagoResponseDTO.class))
        )
    )
    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listar(
            @Parameter(description = "Id del pedido para filtrar (opcional)")
            @RequestParam(required = false) Long pedidoId) {
        if (pedidoId != null) return ResponseEntity.ok(pagoService.listarPorPedido(pedidoId));
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @Operation(summary = "Buscar un pago por id")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Pago encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagoResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> buscarPorId(
            @Parameter(description = "Id del pago") @PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Procesar un nuevo pago",
        description = "Antes de procesar, valida contra ms-pedidos que el pedido exista y " +
                      "esté en estado CONFIRMADO, vía RestTemplate. Crea el pago en estado " +
                      "PENDIENTE y registra la transacción inicial."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Pago procesado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Pedido no encontrado, no confirmado, método de pago no encontrado, " +
                          "o ms-pedidos no responde",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping
    public ResponseEntity<?> procesarPago(@Valid @RequestBody PagoRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.procesarPago(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Actualizar el estado de un pago", description = "Ej: PENDIENTE → APROBADO o RECHAZADO")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Estado actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagoResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PatchMapping("/{id}/estado")
    public ResponseEntity<PagoResponseDTO> actualizarEstado(
            @Parameter(description = "Id del pago") @PathVariable Long id,
            @Parameter(description = "Nuevo estado", example = "APROBADO")
            @RequestParam String estado) {
        try {
            return ResponseEntity.ok(pagoService.actualizarEstado(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ══ TRANSACCIONES ══════════════════════════════════════════════════════════

    @Operation(summary = "Listar transacciones de un pago")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Lista de transacciones obtenida correctamente",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = TransaccionPagoResponseDTO.class))
            )
        ),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}/transacciones")
    public ResponseEntity<List<TransaccionPagoResponseDTO>> listarTransacciones(
            @Parameter(description = "Id del pago") @PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagoService.listarTransacciones(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}