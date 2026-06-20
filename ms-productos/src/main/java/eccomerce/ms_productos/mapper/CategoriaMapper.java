package eccomerce.ms_productos.mapper;

import eccomerce.ms_productos.dto.CategoriaDTO;
import eccomerce.ms_productos.model.Categoria;

/**
 * Mapper entre la entidad Categoria y CategoriaDTO.
 */
public class CategoriaMapper {

    private CategoriaMapper() {}

    public static CategoriaDTO toDTO(Categoria entity) {
        if (entity == null) return null;
        return new CategoriaDTO(
            entity.getId(),
            entity.getNombre(),
            entity.getDescripcion(),
            entity.isActiva()
        );
    }

    public static Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        Categoria entity = new Categoria();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActiva(dto.isActiva());
        return entity;
    }
}
