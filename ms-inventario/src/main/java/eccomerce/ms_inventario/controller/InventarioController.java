package eccomerce.ms_inventario.controller;

import eccomerce.ms_inventario.dto.ErrorResponseDTO;
import eccomerce.ms_inventario.dto.InventarioCreacionRequestDTO;
import eccomerce.ms_inventario.dto.InventarioMovimientoRequestDTO;
import eccomerce.ms_inventario.dto.InventarioResumenResponseDTO;
import eccomerce.ms_inventario.dto.MovimientoInventarioResponseDTO;
import eccomerce.ms_inventario.service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Inventario", description = "Gestión de stock de productos y registro de movimientos")
@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @Operation(summary = "Listar todos los registros de inventario")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de inventario obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = InventarioResumenResponseDTO.class))
        )
    )
    @GetMapping
    public ResponseEntity<List<InventarioResumenResponseDTO>> listar() {
        return ResponseEntity.ok(inventarioService.listarTodos());
    }

    @Operation(summary = "Buscar inventario por ID de producto")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Inventario encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InventarioResumenResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "No existe inventario para ese producto")
    })
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<InventarioResumenResponseDTO> buscarPorProducto(
            @Parameter(description = "ID del producto") @PathVariable Long productoId) {
        return inventarioService.buscarPorProducto(productoId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear un registro de inventario para un producto",
        description = "Antes de crear, valida contra ms-productos que el producto exista y esté activo, vía RestTemplate."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Inventario creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InventarioResumenResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Producto inexistente, inactivo, o ms-productos no responde",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Ya existe un inventario para ese producto",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody InventarioCreacionRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.crear(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Registrar un movimiento de inventario (ENTRADA, SALIDA o AJUSTE)")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Movimiento registrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovimientoInventarioResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o stock insuficiente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @PostMapping("/{id}/movimientos")
    public ResponseEntity<?> registrarMovimiento(
            @Parameter(description = "ID del inventario") @PathVariable Long id,
            @Valid @RequestBody InventarioMovimientoRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.registrarMovimiento(id, dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar movimientos de un inventario")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de movimientos obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = MovimientoInventarioResponseDTO.class))
        )
    )
    @GetMapping("/{id}/movimientos")
    public ResponseEntity<List<MovimientoInventarioResponseDTO>> listarMovimientos(
            @Parameter(description = "ID del inventario") @PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.listarMovimientos(id));
    }
}