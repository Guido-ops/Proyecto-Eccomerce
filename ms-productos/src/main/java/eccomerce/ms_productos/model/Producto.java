package eccomerce.ms_productos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
    @SequenceGenerator(name = "prod_seq", sequenceName = "PROD_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(nullable = false, length = 30)
    private String nombre;

    @NotNull(message = "El precio del producto es obligatorio")
    @Positive(message = "El precio del producto debe ser mayor a cero")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "El stock del producto es obligatorio")
    @Column(nullable = false)
    private Integer stock;

    @Column(unique = true, length = 50)
    private String sku;

    @Column(nullable = false)
    private boolean activo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImagenProducto> imagenes = new ArrayList<>();

    //Constructor vacio
    public Producto() {}

    //Getters
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public Double getPrecio() {return precio;}
    public Integer getStock() {return stock;}
    public String getSku() {return sku;}
    public boolean isActivo() {return activo;}
    public Categoria getCategoria() {return categoria;}
    public Marca getMarca() {return marca;}
    public List<ImagenProducto> getImagenes() {return imagenes;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public void setStock(Integer stock) {this.stock = stock;}
    public void setSku(String sku) {this.sku = sku;}
    public void setActivo(boolean activo) {this.activo = activo;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public void setMarca(Marca marca) {this.marca = marca;}
    public void setImagenes(List<ImagenProducto> imagenes) {this.imagenes = imagenes;}
}
