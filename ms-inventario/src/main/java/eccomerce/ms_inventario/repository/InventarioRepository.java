package eccomerce.ms_inventario.repository;

import eccomerce.ms_inventario.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProductoId(Long productoId);
    boolean existsByProductoId(Long productoId);
    // Buscar inventarios con stock bajo el mínimo
  
    @Query("SELECT i FROM Inventario i WHERE i.stockActual <= i.stockMinimo")
    List<Inventario> findInventariosBajoStock();
}
