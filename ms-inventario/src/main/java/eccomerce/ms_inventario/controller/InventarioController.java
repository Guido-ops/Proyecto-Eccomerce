package eccomerce.ms_inventario.controller;
import eccomerce.ms_inventario.DTO.InventarioCreacionRequestDTO;
import eccomerce.ms_inventario.DTO.InventarioMovimientoRequestDTO;
import eccomerce.ms_inventario.DTO.InventarioResumenResponseDTO;
import eccomerce.ms_inventario.DTO.MovimientoInventarioResponseDTO;
import eccomerce.ms_inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {
    
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
    this.inventarioService = inventarioService;
    }

    @GetMapping
    public ResponseEntity<List<InventarioResumenResponseDTO>> listar() {
        return ResponseEntity.ok(inventarioService.listarTodos());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<InventarioResumenResponseDTO> buscarPorProducto(@PathVariable Long productoId) {
        return inventarioService.buscarPorProducto(productoId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventarioResumenResponseDTO> crear(@Valid @RequestBody InventarioCreacionRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.crear(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/{id}/movimientos")
    public ResponseEntity<MovimientoInventarioResponseDTO> registrarMovimiento(@PathVariable Long id, @Valid @RequestBody InventarioMovimientoRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.registrarMovimiento(id, dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/movimientos")
    public ResponseEntity<List<MovimientoInventarioResponseDTO>> listarMovimientos(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.listarMovimientos(id));
    }
}