package eccomerce.ms_productos.controller;

import eccomerce.ms_productos.dto.*;
import eccomerce.ms_productos.service.ProductoService;
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
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Gestión del catálogo de productos, categorías, marcas e imágenes")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ── Categorias ────────────────────────────────────────────────────────────

    @Operation(
        summary = "Listar todas las categorías",
        description = "Devuelve todas las categorías registradas en el catálogo."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de categorías devuelta correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = CategoriaDTO.class))
        )
    )
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(productoService.listarCategorias());
    }

    @Operation(summary = "Buscar categoría por id")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Categoría encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriaDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoria(
            @Parameter(description = "Id de la categoría", example = "1") @PathVariable Long id) {
        return productoService.buscarCategoriaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear una nueva categoría",
        description = "El JSON editable del Try it out sale desde CategoriaDTO."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Categoría creada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriaDTO.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Ya existe una categoría con ese nombre",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.crearCategoria(categoriaDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Eliminar una categoría por id")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> eliminarCategoria(
            @Parameter(description = "Id de la categoría") @PathVariable Long id) {
        try {
            productoService.eliminarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Marcas ────────────────────────────────────────────────────────────────

    @Operation(summary = "Listar todas las marcas")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de marcas devuelta correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = MarcaDTO.class))
        )
    )
    @GetMapping("/marcas")
    public ResponseEntity<List<MarcaDTO>> listarMarcas() {
        return ResponseEntity.ok(productoService.listarMarcas());
    }

    @Operation(summary = "Buscar marca por id")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Marca encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarcaDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @GetMapping("/marcas/{id}")
    public ResponseEntity<MarcaDTO> buscarMarca(
            @Parameter(description = "Id de la marca", example = "1") @PathVariable Long id) {
        return productoService.buscarMarcaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear una nueva marca",
        description = "El JSON editable del Try it out sale desde MarcaDTO."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Marca creada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarcaDTO.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Ya existe una marca con ese nombre",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping("/marcas")
    public ResponseEntity<?> crearMarca(@Valid @RequestBody MarcaDTO marcaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.crearMarca(marcaDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Eliminar una marca por id")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Marca eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @DeleteMapping("/marcas/{id}")
    public ResponseEntity<?> eliminarMarca(
            @Parameter(description = "Id de la marca") @PathVariable Long id) {
        try {
            productoService.eliminarMarca(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Productos ─────────────────────────────────────────────────────────────

    @Operation(
        summary = "Listar productos",
        description = "Sin parámetros devuelve todos. Filtra por nombre, categoriaId o marcaId (uno a la vez)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de productos devuelta correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ProductoSalidaDTO.class))
        )
    )
    @GetMapping
    public ResponseEntity<List<ProductoSalidaDTO>> listar(
            @Parameter(description = "Filtrar por nombre (búsqueda parcial)")
            @RequestParam(required = false) String nombre,
            @Parameter(description = "Filtrar por id de categoría")
            @RequestParam(required = false) Long categoriaId,
            @Parameter(description = "Filtrar por id de marca")
            @RequestParam(required = false) Long marcaId) {
        if (nombre != null) return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
        if (categoriaId != null) return ResponseEntity.ok(productoService.listarPorCategoria(categoriaId));
        if (marcaId != null) return ResponseEntity.ok(productoService.listarPorMarca(marcaId));
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @Operation(
        summary = "Buscar producto por id",
        description = "Este endpoint es consumido por ms-inventario vía RestTemplate " +
                      "para validar la existencia de un producto."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Producto encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoSalidaDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoSalidaDTO> buscarPorId(
            @Parameter(description = "Id del producto", example = "10") @PathVariable Long id) {
        return productoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear un nuevo producto",
        description = "El JSON editable del Try it out sale desde ProductoEntradaDTO. " +
                      "La categoriaId y marcaId deben existir previamente."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Producto creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoSalidaDTO.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Ya existe un producto con ese SKU",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Categoría o marca no encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ProductoEntradaDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(
        summary = "Actualizar un producto",
        description = "El JSON editable del Try it out sale desde ProductoEntradaDTO."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Producto actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoSalidaDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Producto no encontrado o datos inválidos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "Id del producto") @PathVariable Long id,
            @RequestBody ProductoEntradaDTO dto) {
        try {
            return ResponseEntity.ok(productoService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Eliminar un producto por id")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "Id del producto") @PathVariable Long id) {
        try {
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ── Imagenes ──────────────────────────────────────────────────────────────

    @Operation(summary = "Listar imágenes de un producto")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de imágenes devuelta correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = ImagenProductoDTO.class))
        )
    )
    @GetMapping("/{id}/imagenes")
    public ResponseEntity<List<ImagenProductoDTO>> listarImagenes(
            @Parameter(description = "Id del producto") @PathVariable Long id) {
        return ResponseEntity.ok(productoService.listarImagenes(id));
    }

    @Operation(
        summary = "Agregar una imagen a un producto",
        description = "El JSON editable del Try it out sale desde ImagenProductoDTO."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Imagen agregada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImagenProductoDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Producto no encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping("/{id}/imagenes")
    public ResponseEntity<?> agregarImagen(
            @Parameter(description = "Id del producto") @PathVariable Long id,
            @Valid @RequestBody ImagenProductoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.agregarImagen(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Eliminar una imagen por id")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Imagen eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Imagen no encontrada")
    })
    @DeleteMapping("/imagenes/{id}")
    public ResponseEntity<?> eliminarImagen(
            @Parameter(description = "Id de la imagen") @PathVariable Long id) {
        try {
            productoService.eliminarImagen(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}