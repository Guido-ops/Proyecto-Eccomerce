package eccomerce.usuarios.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "rol_usuario")
public class RolUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(name = "cat_seq", sequenceName = "CAT_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String rolNombre;  // Ejemplos: CLIENTE, ADMIN, VENDEDOR

    @Column(length = 200)
    private String descripcion;

    //Constructor vacio
    public RolUsuario() {}

    //Getters
    public Long getId() { return id; }
    public String getRolNombre() { return rolNombre; }
    public String getDescripcion() { return descripcion; }

    //Setters
    public void setId(Long id) { this.id = id; }
    public void setRolNombre(String rolNombre) { this.rolNombre = rolNombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}