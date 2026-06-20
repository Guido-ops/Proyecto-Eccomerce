package eccomerce.ms_productos.mapper;

import eccomerce.ms_productos.dto.MarcaDTO;
import eccomerce.ms_productos.model.Marca;

/**
 * Mapper entre la entidad Marca y MarcaDTO.
 */
public class MarcaMapper {

    private MarcaMapper() {}

    public static MarcaDTO toDTO(Marca entity) {
        if (entity == null) return null;
        return new MarcaDTO(
            entity.getId(),
            entity.getNombre(),
            entity.getPais_origen(),
            entity.getLogo_url()
        );
    }

    public static Marca toEntity(MarcaDTO dto) {
        if (dto == null) return null;
        Marca entity = new Marca();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setPais_origen(dto.getPaisOrigen());
        entity.setLogo_url(dto.getLogoUrl());
        return entity;
    }
}
