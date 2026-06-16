package eccomerce.ms_pedidos.mapper;

import eccomerce.ms_pedidos.DTO.DetallePedidoEntradaDTO;
import eccomerce.ms_pedidos.DTO.DetallePedidoSalidaDTO;
import eccomerce.ms_pedidos.model.DetallePedido;

/**
 * Mapper entre la entidad DetallePedido y sus DTOs.
 * No copia el campo 'pedido' (lo asigna el service para evitar
 * referencias circulares al serializar).
 */
public class DetallePedidoMapper {

    private DetallePedidoMapper() {}

    public static DetallePedido toEntity(DetallePedidoEntradaDTO dto) {
        if (dto == null) return null;
        DetallePedido entity = new DetallePedido();
        entity.setProductoId(dto.getProductoId());
        entity.setPrecioUnit(dto.getPrecioUnit());
        entity.setCantidad(dto.getCantidad());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }

    public static DetallePedidoSalidaDTO toDTO(DetallePedido entity) {
        if (entity == null) return null;
        return new DetallePedidoSalidaDTO(
            entity.getId(),
            entity.getProductoId(),
            entity.getPrecioUnit(),
            entity.getCantidad(),
            entity.getDescripcion()
        );
    }
}
