package eccomerce.usuarios.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(name = "cat_seq", sequenceName = "CAT_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false)
    private Boolean activo = true;

    // ─── RELACIÓN @ManyToOne → RolUsuario ────────────────────────────────────
    // Genera columna FK: rol_id en la tabla 'usuario'.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private RolUsuario rol;

    // ─── RELACIÓN @OneToMany → Direccion ─────────────────────────────────────
    // La FK usuario_id vive en la tabla 'direccion'.
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Direccion> direcciones = new ArrayList<>();

    //Constructor vacio
    public Usuario() {}

    //Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Boolean getActivo() { return activo; }
    public RolUsuario getRol() { return rol; }
    public List<Direccion> getDirecciones() { return direcciones; }

    //Setters
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setActivo(Boolean activo) { this.activo = activo ; }
    public void setRol(RolUsuario rol) { this.rol = rol; }
    public void setDirecciones(List<Direccion> d) { this.direcciones = d; }
}