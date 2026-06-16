package com.eccomerce.usuarios.controller;

import com.eccomerce.usuarios.DTO.DireccionDTO;
import com.eccomerce.usuarios.DTO.RolDTO;
import com.eccomerce.usuarios.DTO.UsuarioRequestDTO;
import com.eccomerce.usuarios.DTO.UsuarioResponseDTO;
import com.eccomerce.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 * Este controlador maneja las solicitudes HTTP relacionadas con los usuarios, roles y direcciones en el sistema de gestión de usuarios.
 * Controlador para gestionar las operaciones relacionadas con los usuarios.
 * Incluye endpoints para listar, crear, actualizar y eliminar usuarios, así como para gestionar roles y direcciones asociadas a los usuarios.
 * Utiliza el servicio UsuarioService para realizar las operaciones de negocio y devuelve respuestas HTTP adecuadas según el resultado de cada operación.
 */

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ══ ROLES ═════════════════════════════════════════════════════════════════

    @GetMapping("/roles")
    public ResponseEntity<List<RolDTO>> listarRoles() {
        return ResponseEntity.ok(usuarioService.listarRoles());
    }

    @PostMapping("/roles")
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolDTO DTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearRol(DTO));
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id) {
        try {
            usuarioService.eliminarRol(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ══ USUARIOS ══════════════════════════════════════════════════════════════

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO DTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(DTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Long id,
                                        @RequestBody UsuarioRequestDTO DTO) {
        try {
            return ResponseEntity.ok(usuarioService.actualizar(id, DTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ══ DIRECCIONES ═══════════════════════════════════════════════════════════

    @GetMapping("/{id}/direcciones")
    public ResponseEntity<List<DireccionDTO>> listarDirecciones(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.listarDirecciones(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/direcciones")
    public ResponseEntity<DireccionDTO> agregarDireccion(@PathVariable Long id,
                                                        @Valid @RequestBody DireccionDTO DTO) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.agregarDireccion(id, DTO));
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

    @DeleteMapping("/direcciones/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Long id) {
        try {
            usuarioService.eliminarDireccion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
