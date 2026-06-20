package eccomerce.ms_productos.repository;

import eccomerce.ms_productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Buscar por categoría: usa el campo 'categoria.id' del objeto Producto
    // SQL: SELECT * FROM producto WHERE categoria_id = ?
    List<Producto> findByCategoriaId(Long categoria);

    // Buscar por marca: usa el campo 'marca.id'
    // SQL: SELECT * FROM producto WHERE marca_id = ?
    List<Producto> findByMarcaId(Long marcaId);

    // Buscar solo activos
    List<Producto> findByActivoTrue();

    // Buscar por nombre (case insensitive, búsqueda parcial)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por SKU
    Optional<Producto> findBySku(String sku);
    boolean existsBySku(String sku);

    List<Producto> findByActivo(boolean b);
}