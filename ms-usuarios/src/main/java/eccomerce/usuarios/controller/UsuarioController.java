package eccomerce.usuarios.controller;

import eccomerce.usuarios.dto.DireccionDTO;
import eccomerce.usuarios.dto.ErrorResponseDTO;
import eccomerce.usuarios.dto.RolDTO;
import eccomerce.usuarios.dto.UsuarioRequestDTO;
import eccomerce.usuarios.dto.UsuarioResponseDTO;
import eccomerce.usuarios.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios, roles y direcciones de despacho")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ══ ROLES ═════════════════════════════════════════════════════════════════

    @Operation(summary = "Listar todos los roles")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de roles obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = RolDTO.class))
        )
    )
    @GetMapping("/roles")
    public ResponseEntity<List<RolDTO>> listarRoles() {
        return ResponseEntity.ok(usuarioService.listarRoles());
    }

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Rol creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolDTO.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Ya existe un rol con ese nombre",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping("/roles")
    public ResponseEntity<?> crearRol(@Valid @RequestBody RolDTO DTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearRol(DTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Eliminar un rol por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Rol eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> eliminarRol(
            @Parameter(description = "ID del rol a eliminar") @PathVariable Long id) {
        try {
            usuarioService.eliminarRol(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ══ USUARIOS ══════════════════════════════════════════════════════════════

    @Operation(summary = "Listar todos los usuarios")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de usuarios obtenida correctamente",
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDTO.class))
        )
    )
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Operation(summary = "Buscar usuario por ID")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @Parameter(description = "ID del usuario") @PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Registra un usuario nuevo. El email debe ser único y el rolId debe existir previamente."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Usuario creado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Email ya registrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Rol inexistente o datos inválidos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody UsuarioRequestDTO DTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(DTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Usuario actualizado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable Long id,
            @RequestBody UsuarioRequestDTO DTO) {
        try {
            return ResponseEntity.ok(usuarioService.actualizar(id, DTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Eliminar un usuario por ID", description = "Baja lógica: el usuario se desactiva, no se borra de la base de datos.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuario desactivado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del usuario a eliminar") @PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ══ DIRECCIONES ═══════════════════════════════════════════════════════════

    @Operation(summary = "Listar direcciones de un usuario")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Direcciones del usuario obtenidas",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = DireccionDTO.class))
            )
        ),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}/direcciones")
    public ResponseEntity<List<DireccionDTO>> listarDirecciones(
            @Parameter(description = "ID del usuario") @PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.listarDirecciones(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Agregar una dirección a un usuario")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Dirección agregada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DireccionDTO.class))
        ),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PostMapping("/{id}/direcciones")
    public ResponseEntity<DireccionDTO> agregarDireccion(
            @Parameter(description = "ID del usuario") @PathVariable Long id,
            @Valid @RequestBody DireccionDTO DTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarDireccion(id, DTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una dirección por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Dirección eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    @DeleteMapping("/direcciones/{id}")
    public ResponseEntity<?> eliminarDireccion(
            @Parameter(description = "ID de la dirección a eliminar") @PathVariable Long id) {
        try {
            usuarioService.eliminarDireccion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}