package eccomerce.ms_pedidos.mapper;

import eccomerce.ms_pedidos.DTO.DetallePedidoSalidaDTO;
import eccomerce.ms_pedidos.DTO.PedidoEntradaDTO;
import eccomerce.ms_pedidos.DTO.PedidoSalidaDTO;
import eccomerce.ms_pedidos.model.DetallePedido;
import eccomerce.ms_pedidos.model.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mapper entre la entidad Pedido y sus DTOs (entrada y salida).
 * La conversion del DTO de entrada deja sin asignar el estado: el
 * service lo carga desde la BD por id (o usa el estado por defecto).
 */
public class PedidoMapper {

    private PedidoMapper() {}

    /**
     * PedidoEntradaDTO -> Pedido. Solo copia los datos basicos y los
     * articulos como entidades DetallePedido. El service se encarga
     * de enlazarlos con el pedido y de cargar el EstadoPedido.
     */
    public static Pedido toEntity(PedidoEntradaDTO dto) {
        if (dto == null) return null;
        Pedido entity = new Pedido();
        entity.setUsuarioId(dto.getUsuarioId());
        entity.setTotal(dto.getTotal());

        List<DetallePedido> detalles = new ArrayList<>();
        if (dto.getArticulos() != null) {
            for (var articuloDTO : dto.getArticulos()) {
                detalles.add(DetallePedidoMapper.toEntity(articuloDTO));
            }
        }
        entity.setDetalles(detalles);
        return entity;
    }

    /**
     * Pedido -> PedidoSalidaDTO. Aplana la relacion con EstadoPedido y
     * con la lista de detalles a sus DTOs correspondientes.
     */
    public static PedidoSalidaDTO toDTO(Pedido entity) {
        if (entity == null) return null;

        List<DetallePedidoSalidaDTO> articulos = entity.getDetalles() == null
            ? Collections.emptyList()
            : entity.getDetalles().stream()
                .map(DetallePedidoMapper::toDTO)
                .toList();

        return new PedidoSalidaDTO(
            entity.getId(),
            entity.getFechaPedido(),
            entity.getTotal(),
            entity.getUsuarioId(),
            EstadoPedidoMapper.toDTO(entity.getEstado()),
            articulos
        );
    }
}
