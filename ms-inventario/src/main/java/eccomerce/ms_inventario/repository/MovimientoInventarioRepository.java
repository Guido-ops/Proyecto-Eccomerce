package eccomerce.ms_inventario.repository;

import eccomerce.ms_inventario.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    
    List<MovimientoInventario> findByInventarioIdOrderByFechaDesc(Long inventarioId);
    List<MovimientoInventario> findByTipo(String tipo);
}
