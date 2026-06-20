package eccomerce.usuarios.service;

import eccomerce.usuarios.dto.DireccionDTO;
import eccomerce.usuarios.dto.RolDTO;
import eccomerce.usuarios.dto.UsuarioRequestDTO;
import eccomerce.usuarios.dto.UsuarioResponseDTO;
import eccomerce.usuarios.mapper.UsuarioMapper;
import eccomerce.usuarios.model.Direccion;
import eccomerce.usuarios.model.RolUsuario;
import eccomerce.usuarios.model.Usuario;
import eccomerce.usuarios.repository.DireccionRepository;
import eccomerce.usuarios.repository.RolUsuarioRepository;
import eccomerce.usuarios.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository    usuarioRepository;
    private final RolUsuarioRepository rolRepository;
    private final DireccionRepository  direccionRepository;
    private final UsuarioMapper        mapper;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolUsuarioRepository rolRepository,
                          DireccionRepository direccionRepository,
                          UsuarioMapper mapper) {
        this.usuarioRepository  = usuarioRepository;
        this.rolRepository      = rolRepository;
        this.direccionRepository = direccionRepository;
        this.mapper             = mapper;
    }

    // ══ ROLES ═════════════════════════════════════════════════════════════════

    public List<RolDTO> listarRoles() {
        return rolRepository.findAll()
                .stream()
                .map(mapper::toRolDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RolDTO crearRol(RolDTO dto) {
        if (rolRepository.existsByRolNombre(dto.getRolNombre()))
            throw new IllegalArgumentException("Rol ya existe: " + dto.getRolNombre());

        RolUsuario rol = new RolUsuario();
        rol.setRolNombre(dto.getRolNombre());
        rol.setDescripcion(dto.getDescripcion());

        return mapper.toRolDTO(rolRepository.save(rol));
    }

    @Transactional
    public void eliminarRol(@NonNull Long id) {
        if (!rolRepository.existsById(id))
            throw new RuntimeException("Rol no encontrado: " + id);
        rolRepository.deleteById(id);
    }

    // ══ USUARIOS ══════════════════════════════════════════════════════════════

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findByActivo(true)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(@NonNull Long id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        return mapper.toResponseDTO(u);
    }

    @Transactional
    public UsuarioResponseDTO crear(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Email ya registrado: " + dto.getEmail());

        RolUsuario rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + dto.getRolId()));

        Usuario nuevo = mapper.toEntity(dto, rol, dto.getPassword()); // En producción, hash de contraseña  
        Usuario guardado = usuarioRepository.save(nuevo);

        log.info("[UsuarioService] Usuario creado id={}", guardado.getId());
        return mapper.toResponseDTO(guardado);
    }

    @Transactional
    public UsuarioResponseDTO actualizar(@NonNull Long id, UsuarioRequestDTO dto) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));

        if (dto.getNombre() != null) u.setNombre(dto.getNombre());

        return mapper.toResponseDTO(usuarioRepository.save(u));
    }

    @Transactional
    public void eliminar(@NonNull Long id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        u.setActivo(false);
        usuarioRepository.save(u);
        log.info("[UsuarioService] Usuario desactivado id={}", id);
    }

    // ══ DIRECCIONES ═══════════════════════════════════════════════════════════

    public List<DireccionDTO> listarDirecciones(@NonNull Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId))
            throw new RuntimeException("Usuario no encontrado: " + usuarioId);
        return direccionRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDireccionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DireccionDTO agregarDireccion(@NonNull Long usuarioId, DireccionDTO dto) {
        Usuario u = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + usuarioId));

        Direccion direccion = mapper.toDireccionEntity(dto, u);
        return mapper.toDireccionDTO(direccionRepository.save(direccion));
    }

    @Transactional
    public void eliminarDireccion(@NonNull Long id) {
        if (!direccionRepository.existsById(id))
            throw new RuntimeException("Dirección no encontrada: " + id);
        direccionRepository.deleteById(id);
    }

}