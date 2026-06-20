package eccomerce.usuarios.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "DIRECCION")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(name = "cat_seq", sequenceName = "CAT_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "La calle es obligatoria")
    @Column(nullable = false, length = 200)
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(length = 80)
    private String region;

    // ─── RELACIÓN @ManyToOne → Usuario ───────────────────────────────────────
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    public Direccion() {}

    //Getters
    public Long getId() { return id; }
    public String getCalle() { return calle; }
    public String getCiudad() { return ciudad; }
    public String getRegion() { return region; }
    public Usuario getUsuario() { return usuario; }
    
    //Setters
    public void setId(Long id) { this.id = id; }
    public void setCalle(String calle) { this.calle = calle; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setRegion(String region) { this.region = region; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}