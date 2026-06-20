package eccomerce.ms_pedidos.mapper;

import eccomerce.ms_pedidos.dto.EstadoPedidoDTO;
import eccomerce.ms_pedidos.model.EstadoPedido;

/**
 * Mapper entre la entidad EstadoPedido y EstadoPedidoDTO.
 */
public class EstadoPedidoMapper {

    private EstadoPedidoMapper() {}

    public static EstadoPedidoDTO toDTO(EstadoPedido entity) {
        if (entity == null) return null;
        return new EstadoPedidoDTO(
            entity.getId(),
            entity.getNombre(),
            entity.getDescripcion()
        );
    }

    public static EstadoPedido toEntity(EstadoPedidoDTO dto) {
        if (dto == null) return null;
        EstadoPedido entity = new EstadoPedido();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
