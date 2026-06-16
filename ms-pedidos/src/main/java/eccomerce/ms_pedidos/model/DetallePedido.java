package eccomerce.ms_pedidos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DETALLE_PEDIDO")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(name = "cat_seq", sequenceName = "CAT_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @NotNull(message = "El ID del producto es obligatorio")
    @Column(name = "PRODUCTO_ID", nullable = false)
    private Long productoId;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a cero")
    @Column(name = "PRECIO_UNIT", nullable = false)
    private Double precioUnit;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEDIDO_ID", nullable = false)
    @JsonIgnore
    private Pedido pedido;

    //Constructor vacio
    public DetallePedido() {}

    //Getters
    public Long getId() {return id;}
    public String getDescripcion() {return descripcion;}
    public Long getProductoId() {return productoId;}
    public Double getPrecioUnit() {return precioUnit;}
    public Integer getCantidad() {return cantidad;}
    public Pedido getPedido() {return pedido;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setProductoId(Long productoId) {this.productoId = productoId;}
    public void setPrecioUnit(Double precioUnit) {this.precioUnit = precioUnit;}
    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}
    public void setPedido(Pedido pedido) {this.pedido = pedido;}
}
