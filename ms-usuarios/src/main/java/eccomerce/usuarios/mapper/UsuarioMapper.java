package eccomerce.usuarios.mapper;

import eccomerce.usuarios.dto.DireccionDTO;
import eccomerce.usuarios.dto.RolDTO;
import eccomerce.usuarios.dto.UsuarioRequestDTO;
import eccomerce.usuarios.dto.UsuarioResponseDTO;
import eccomerce.usuarios.model.Direccion;
import eccomerce.usuarios.model.RolUsuario;
import eccomerce.usuarios.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    // ── RolUsuario → RolDTO ───────────────────────────────────────────────────

    public RolDTO toRolDTO(RolUsuario rol) {
        if (rol == null) return null;
        return new RolDTO(rol.getId(), rol.getRolNombre(), rol.getDescripcion());
    }

    // ── Direccion → DireccionDTO ──────────────────────────────────────────────

    public DireccionDTO toDireccionDTO(Direccion d) {
        if (d == null) return null;
        return new DireccionDTO(d.getId(), d.getCalle(), d.getCiudad(), d.getRegion());
    }

    public List<DireccionDTO> toDireccionDTOList(List<Direccion> lista) {
        if (lista == null || lista.isEmpty()) return Collections.emptyList();
        return lista.stream().map(this::toDireccionDTO).collect(Collectors.toList());
    }

    // ── Usuario → UsuarioResponseDTO ─────────────────────────────────────────

    public UsuarioResponseDTO toResponseDTO(Usuario u) {
        return new UsuarioResponseDTO(
            u.getId(),
            u.getNombre(),
            u.getEmail(),
            u.getActivo(),
            toRolDTO(u.getRol()),
            toDireccionDTOList(u.getDirecciones())
        );
    }

    // ── UsuarioRequestDTO → Usuario (entidad nueva) ───────────────────────────
 
    public Usuario toEntity(UsuarioRequestDTO dto, RolUsuario rol, String passwordEncriptada) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncriptada);
        usuario.setRol(rol);
        usuario.setActivo(true);
        return usuario;
    }

    // ── DireccionDTO → Direccion (entidad nueva) ──────────────────────────────
 
    public Direccion toDireccionEntity(DireccionDTO dto, Usuario usuario) {
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setCiudad(dto.getCiudad());
        direccion.setRegion(dto.getRegion());
        direccion.setUsuario(usuario);
        return direccion;
    }
}