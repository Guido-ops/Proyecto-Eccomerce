package eccomerce.ms_pedidos.controller;

import eccomerce.ms_pedidos.dto.*;
import eccomerce.ms_pedidos.service.PedidoService;
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

@Tag(name = "Pedidos", description = "Gestión del ciclo de vida de pedidos, estados y artículos")
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // ── Estados de Pedido ─────────────────────────────────────────────────────

    @Operation(summary = "Listar todos los estados de pedido")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de estados obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = EstadoPedidoDTO.class))
        )
    )
    @GetMapping("/estados")
    public ResponseEntity<List<EstadoPedidoDTO>> listarEstados() {
        return ResponseEntity.ok(pedidoService.listarEstados());
    }

    @Operation(summary = "Buscar un estado de pedido por ID")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Estado encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoPedidoDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Estado no encontrado")
    })
    @GetMapping("/estados/{id}")
    public ResponseEntity<EstadoPedidoDTO> buscarEstadoPorId(
            @Parameter(description = "ID del estado") @PathVariable Long id) {
        return pedidoService.buscarEstadoPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo estado de pedido")
    @ApiResponse(
        responseCode = "201",
        description = "Estado creado exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadoPedidoDTO.class))
    )
    @PostMapping("/estados")
    public ResponseEntity<EstadoPedidoDTO> crearEstado(@Valid @RequestBody EstadoPedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crearEstado(dto));
    }

    // ── Pedidos ───────────────────────────────────────────────────────────────

    @Operation(summary = "Listar pedidos, opcionalmente filtrados por usuario")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de pedidos obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = PedidoSalidaDTO.class))
        )
    )
    @GetMapping
    public ResponseEntity<List<PedidoSalidaDTO>> listar(
            @Parameter(description = "ID del usuario para filtrar (opcional)") @RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) return ResponseEntity.ok(pedidoService.listarPorUsuario(usuarioId));
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @Operation(summary = "Buscar un pedido por ID")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Pedido encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoSalidaDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoSalidaDTO> buscarPorId(
            @Parameter(description = "ID del pedido") @PathVariable Long id) {
        return pedidoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear un pedido completo con sus artículos",
        description = "Crea la cabecera y los artículos en una sola operación. " +
                      "El sistema asigna fecha, estado por defecto (PENDIENTE) y recalcula el total."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Pedido creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoSalidaDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o estado inexistente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PedidoEntradaDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Cambiar el estado de un pedido")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Estado del pedido actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoSalidaDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Pedido o estado no encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(
            @Parameter(description = "ID del pedido") @PathVariable Long id,
            @Parameter(description = "ID del nuevo estado") @RequestParam Long estadoId) {
        try {
            return ResponseEntity.ok(pedidoService.cambiarEstado(id, estadoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Listar artículos de un pedido")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de artículos obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = DetallePedidoSalidaDTO.class))
        )
    )
    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetallePedidoSalidaDTO>> listarDetalles(
            @Parameter(description = "ID del pedido") @PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.listarDetalles(id));
    }

    @Operation(summary = "Eliminar un pedido por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del pedido a eliminar") @PathVariable Long id) {
        try {
            pedidoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}