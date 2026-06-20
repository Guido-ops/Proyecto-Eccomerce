package eccomerce.usuarios.repository;
import eccomerce.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    // SQL: SELECT * FROM usuario WHERE rol_id = ?
    List<Usuario> findByRolId(Long rolId);
    List<Usuario> findByActivo(Boolean activo);
}