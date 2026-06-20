package eccomerce.ms_productos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eccomerce.ms_productos.model.ImagenProducto;


@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {
    // SQL: SELECT * FROM imagen_producto WHERE producto_id = ? ORDER BY orden
    List<ImagenProducto> findByProductoIdOrderByOrden(Long productoId);
}
