package eccomerce.ms_pedidos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "ESTADO_PEDIDO")
public class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(name = "cat_seq", sequenceName = "CAT_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String descripcion;

    //Constructor vacio
    public EstadoPedido() {}

    //Getters
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public String getDescripcion() {return descripcion;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
