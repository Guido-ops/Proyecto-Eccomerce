package eccomerce.ms_pedidos.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import eccomerce.ms_pedidos.model.Pedido;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioId(Long UsuarioId);
    List<Pedido> findByEstadoId(Long EstadoId);

}
