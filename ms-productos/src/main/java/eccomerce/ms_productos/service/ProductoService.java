package eccomerce.ms_productos.service;

import eccomerce.ms_productos.dto.*;
import eccomerce.ms_productos.mapper.*;
import eccomerce.ms_productos.model.*;
import eccomerce.ms_productos.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    @Autowired private ProductoRepository productoRepository;
    @Autowired private CategoriaRepository categoriaRepository;
    @Autowired private MarcaRepository marcaRepository;
    @Autowired private ImagenProductoRepository imagenRepository;

    // ── CRUD Categoria ────────────────────────────────────────────────────────
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findByActivaTrue().stream()
            .map(CategoriaMapper::toDTO)
            .toList();
    }

    public Optional<CategoriaDTO> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).map(CategoriaMapper::toDTO);
    }

    @Transactional
    public CategoriaDTO crearCategoria(CategoriaDTO dto) {
        if (categoriaRepository.existsByNombre(dto.getNombre()))
            throw new IllegalArgumentException("Ya existe la categoria: " + dto.getNombre());
        Categoria entity = CategoriaMapper.toEntity(dto);
        entity.setId(null); // El sistema asigna el id
        return CategoriaMapper.toDTO(categoriaRepository.save(entity));
    }

    @Transactional
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id))
            throw new RuntimeException("Categoria no encontrada: " + id);
        categoriaRepository.deleteById(id);
    }

    // ── CRUD Marca ────────────────────────────────────────────────────────────
    public List<MarcaDTO> listarMarcas() {
        return marcaRepository.findAll().stream()
            .map(MarcaMapper::toDTO)
            .toList();
    }

    public Optional<MarcaDTO> buscarMarcaPorId(Long id) {
        return marcaRepository.findById(id).map(MarcaMapper::toDTO);
    }

    @Transactional
    public MarcaDTO crearMarca(MarcaDTO dto) {
        if (marcaRepository.existsByNombre(dto.getNombre()))
            throw new IllegalArgumentException("Ya existe la marca: " + dto.getNombre());
        Marca entity = MarcaMapper.toEntity(dto);
        entity.setId(null);
        return MarcaMapper.toDTO(marcaRepository.save(entity));
    }

    @Transactional
    public void eliminarMarca(Long id) {
        if (!marcaRepository.existsById(id))
            throw new RuntimeException("Marca no encontrada: " + id);
        marcaRepository.deleteById(id);
    }

    // ── CRUD Producto ─────────────────────────────────────────────────────────
    public List<ProductoSalidaDTO> listarTodos() {
        return productoRepository.findByActivoTrue().stream()
            .map(ProductoMapper::toDTO)
            .toList();
    }

    public Optional<ProductoSalidaDTO> buscarPorId(Long id) {
        return productoRepository.findById(id).map(ProductoMapper::toDTO);
    }

    public List<ProductoSalidaDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream()
            .map(ProductoMapper::toDTO)
            .toList();
    }

    public List<ProductoSalidaDTO> listarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId).stream()
            .map(ProductoMapper::toDTO)
            .toList();
    }

    public List<ProductoSalidaDTO> listarPorMarca(Long marcaId) {
        return productoRepository.findByMarcaId(marcaId).stream()
            .map(ProductoMapper::toDTO)
            .toList();
    }

    /**
     * Crea un producto a partir de un ProductoEntradaDTO.
     * El cliente envia solo nombre, precio, stock, sku, categoriaId y marcaId
     * (opcional). El service carga las entidades de Categoria/Marca por id y
     * arma el grafo JPA.
     */
    @Transactional
    public ProductoSalidaDTO crear(ProductoEntradaDTO dto) {
        log.info("[ProductoService] Creando producto: {}", dto.getNombre());

        if (dto.getSku() != null && productoRepository.existsBySku(dto.getSku()))
            throw new IllegalArgumentException("SKU duplicado: " + dto.getSku());

        Producto producto = ProductoMapper.toEntity(dto);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + dto.getCategoriaId()));
        producto.setCategoria(categoria);

        if (dto.getMarcaId() != null) {
            Marca marca = marcaRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada: " + dto.getMarcaId()));
            producto.setMarca(marca);
        }

        Producto guardado = productoRepository.save(producto);
        log.info("[ProductoService] Producto creado id={}", guardado.getId());
        return ProductoMapper.toDTO(guardado);
    }

    /**
     * Actualizacion parcial: solo se aplican los campos no nulos del DTO.
     */
    @Transactional
    public ProductoSalidaDTO actualizar(Long id, ProductoEntradaDTO dto) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));

        if (dto.getNombre() != null) producto.setNombre(dto.getNombre());
        if (dto.getPrecio() != null) producto.setPrecio(dto.getPrecio());
        if (dto.getStock() != null) producto.setStock(dto.getStock());
        if (dto.getSku() != null) producto.setSku(dto.getSku());

        if (dto.getCategoriaId() != null) {
            Categoria cat = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + dto.getCategoriaId()));
            producto.setCategoria(cat);
        }
        if (dto.getMarcaId() != null) {
            Marca marca = marcaRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada: " + dto.getMarcaId()));
            producto.setMarca(marca);
        }
        return ProductoMapper.toDTO(productoRepository.save(producto));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id))
            throw new RuntimeException("Producto no encontrado: " + id);
        productoRepository.deleteById(id);
    }

    // ── Imagenes ──────────────────────────────────────────────────────────────
    public List<ImagenProductoDTO> listarImagenes(Long productoId) {
        return imagenRepository.findByProductoIdOrderByOrden(productoId).stream()
            .map(ImagenProductoMapper::toDTO)
            .toList();
    }

    @Transactional
    public ImagenProductoDTO agregarImagen(Long productoId, ImagenProductoDTO dto) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoId));
        ImagenProducto imagen = ImagenProductoMapper.toEntity(dto);
        imagen.setId(null);
        imagen.setProducto(producto);
        return ImagenProductoMapper.toDTO(imagenRepository.save(imagen));
    }

    @Transactional
    public void eliminarImagen(Long id) {
        if (!imagenRepository.existsById(id))
            throw new RuntimeException("Imagen no encontrada: " + id);
        imagenRepository.deleteById(id);
    }
}
