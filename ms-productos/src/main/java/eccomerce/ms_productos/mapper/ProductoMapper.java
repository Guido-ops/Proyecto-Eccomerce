package eccomerce.ms_productos.mapper;

import eccomerce.ms_productos.dto.ImagenProductoDTO;
import eccomerce.ms_productos.dto.ProductoEntradaDTO;
import eccomerce.ms_productos.dto.ProductoSalidaDTO;
import eccomerce.ms_productos.model.Producto;

import java.util.Collections;
import java.util.List;

/**
 * Mapper entre la entidad Producto y sus DTOs (entrada y salida).
 * La conversion de salida incluye CategoriaDTO, MarcaDTO y la lista de
 * imagenes ya mapeadas a su DTO, produciendo una estructura plana sin
 * referencias circulares.
 */
public class ProductoMapper {

    private ProductoMapper() {}

    /**
     * ProductoEntradaDTO -> Producto (sin categoria/marca: las setea el service
     * despues de cargarlas desde la BD por su id).
     */
    public static Producto toEntity(ProductoEntradaDTO dto) {
        if (dto == null) return null;
        Producto entity = new Producto();
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setSku(dto.getSku());
        entity.setActivo(true);
        return entity;
    }

    /**
     * Producto -> ProductoSalidaDTO.
     * Aplana las relaciones a Categoria, Marca e Imagenes en sus DTOs.
     */
    public static ProductoSalidaDTO toDTO(Producto entity) {
        if (entity == null) return null;

        List<ImagenProductoDTO> imagenes = entity.getImagenes() == null
            ? Collections.emptyList()
            : entity.getImagenes().stream()
                .map(ImagenProductoMapper::toDTO)
                .toList();

        return new ProductoSalidaDTO(
            entity.getId(),
            entity.getNombre(),
            entity.getPrecio(),
            entity.getStock(),
            entity.getSku(),
            entity.isActivo(),
            CategoriaMapper.toDTO(entity.getCategoria()),
            MarcaMapper.toDTO(entity.getMarca()),
            imagenes
        );
    }
}
