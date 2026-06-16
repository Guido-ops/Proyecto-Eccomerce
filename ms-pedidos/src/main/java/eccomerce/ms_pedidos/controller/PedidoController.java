package eccomerce.ms_pedidos.controller;

import eccomerce.ms_pedidos.DTO.*;
import eccomerce.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired private PedidoService pedidoService;

    // ── Estados de Pedido ─────────────────────────────────────────────────────
    @GetMapping("/estados")
    public ResponseEntity<List<EstadoPedidoDTO>> listarEstados() {
        return ResponseEntity.ok(pedidoService.listarEstados());
    }

    @GetMapping("/estados/{id}")
    public ResponseEntity<?> buscarEstadoPorId(@PathVariable Long id) {
        return pedidoService.buscarEstadoPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/estados")
    public ResponseEntity<EstadoPedidoDTO> crearEstado(@Valid @RequestBody EstadoPedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crearEstado(dto));
    }

    // ── Pedidos ───────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<PedidoSalidaDTO>> listar(
            @RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) return ResponseEntity.ok(pedidoService.listarPorUsuario(usuarioId));
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un pedido completo (cabecera + articulos) a partir de
     * PedidoEntradaDTO. El sistema asigna fecha, estado por defecto y
     * recalcula el total.
     */
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PedidoEntradaDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestParam Long estadoId) {
        try {
            return ResponseEntity.ok(pedidoService.cambiarEstado(id, estadoId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetallePedidoSalidaDTO>> listarDetalles(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.listarDetalles(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            pedidoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
