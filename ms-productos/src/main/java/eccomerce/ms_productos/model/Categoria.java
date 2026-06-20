package eccomerce.ms_productos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(name = "cat_seq", sequenceName = "CAT_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "El nombre no puede ser vacio")
    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(length = 200)
    private String descripcion;
    
    
    @Column(nullable = false)
    private boolean activa;

    //Constructor vacio
    public Categoria() {}

    //Getters
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public boolean isActiva() {return activa;}
    public String getDescripcion() {return descripcion;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setActiva(boolean activa) {this.activa = activa;}
}
