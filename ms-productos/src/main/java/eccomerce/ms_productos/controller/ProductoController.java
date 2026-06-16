package eccomerce.ms_productos.controller;

import eccomerce.ms_productos.DTO.*;
import eccomerce.ms_productos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired private ProductoService productoService;

    // ── Categorias ────────────────────────────────────────────────────────────
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(productoService.listarCategorias());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable Long id) {
        return productoService.buscarCategoriaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.crearCategoria(categoriaDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        try {
            productoService.eliminarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Marcas ────────────────────────────────────────────────────────────────
    @GetMapping("/marcas")
    public ResponseEntity<List<MarcaDTO>> listarMarcas() {
        return ResponseEntity.ok(productoService.listarMarcas());
    }

    @GetMapping("/marcas/{id}")
    public ResponseEntity<?> buscarMarca(@PathVariable Long id) {
        return productoService.buscarMarcaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/marcas")
    public ResponseEntity<?> crearMarca(@Valid @RequestBody MarcaDTO marcaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.crearMarca(marcaDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/marcas/{id}")
    public ResponseEntity<?> eliminarMarca(@PathVariable Long id) {
        try {
            productoService.eliminarMarca(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Productos ─────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<ProductoSalidaDTO>> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Long marcaId) {
        if (nombre != null) return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
        if (categoriaId != null) return ResponseEntity.ok(productoService.listarPorCategoria(categoriaId));
        if (marcaId != null) return ResponseEntity.ok(productoService.listarPorMarca(marcaId));
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ProductoEntradaDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody ProductoEntradaDTO dto) {
        try {
            return ResponseEntity.ok(productoService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Imagenes ──────────────────────────────────────────────────────────────
    @GetMapping("/{id}/imagenes")
    public ResponseEntity<List<ImagenProductoDTO>> listarImagenes(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.listarImagenes(id));
    }

    @PostMapping("/{id}/imagenes")
    public ResponseEntity<?> agregarImagen(@PathVariable Long id,
                                           @Valid @RequestBody ImagenProductoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.agregarImagen(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/imagenes/{id}")
    public ResponseEntity<?> eliminarImagen(@PathVariable Long id) {
        try {
            productoService.eliminarImagen(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
