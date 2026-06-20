package eccomerce.ms_productos.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "IMAGENES_PRODUCTOS")
public class ImagenProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "img_prod_seq")
    @SequenceGenerator(name = "img_prod_seq", sequenceName = "img_prod_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    @Column(nullable = false, length = 200)
    private String url;

    @Column(nullable = false)
    private Integer orden = 0;

    @Column(nullable = false)
    private boolean principal = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonIgnore
    private Producto producto;

    //Constructor vacio
    public ImagenProducto() {}

    //Getters
    public Long getId() {return id;}
    public String getUrl() {return url;}
    public Integer getOrden() {return orden;}
    public boolean isPrincipal() {return principal;}
    public Producto getProducto() {return producto;}
    //Setters
    public void setId(Long id) {this.id = id;}
    public void setUrl(String url) {this.url = url;}
    public void setOrden(Integer orden) {this.orden = orden;}
    public void setPrincipal(boolean principal) {this.principal = principal;}
    public void setProducto(Producto producto) {this.producto = producto;}
    

}
