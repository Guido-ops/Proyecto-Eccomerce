package eccomerce.usuarios.repository;
import eccomerce.usuarios.model.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {
    Optional<RolUsuario> findByRolNombre(String rolNombre);
    boolean existsByRolNombre(String rolNombre);
}