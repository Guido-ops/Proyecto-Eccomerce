package eccomerce.ms_productos.mapper;

import eccomerce.ms_productos.dto.ImagenProductoDTO;
import eccomerce.ms_productos.model.ImagenProducto;

/**
 * Mapper entre la entidad ImagenProducto y ImagenProductoDTO.
 * Solo expone los datos publicos de la imagen, sin la referencia al Producto.
 */
public class ImagenProductoMapper {

    private ImagenProductoMapper() {}

    public static ImagenProductoDTO toDTO(ImagenProducto entity) {
        if (entity == null) return null;
        return new ImagenProductoDTO(
            entity.getId(),
            entity.getUrl(),
            entity.getOrden(),
            entity.isPrincipal()
        );
    }

    public static ImagenProducto toEntity(ImagenProductoDTO dto) {
        if (dto == null) return null;
        ImagenProducto entity = new ImagenProducto();
        entity.setId(dto.getId());
        entity.setUrl(dto.getUrl());
        entity.setOrden(dto.getOrden() != null ? dto.getOrden() : 0);
        entity.setPrincipal(dto.isPrincipal());
        return entity;
    }
}
