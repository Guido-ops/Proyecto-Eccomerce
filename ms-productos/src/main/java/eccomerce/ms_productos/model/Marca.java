package eccomerce.ms_productos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "MARCAS")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marca_seq")
    @SequenceGenerator(name = "marca_seq", sequenceName = "marca_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Column(nullable = false, unique = true, length = 80)
    private String nombre;

    @Column(name = "PAIS_ORIGEN", length = 80)
    private String pais_origen;

    @Column(name = "LOGO_URL", length = 300)
    private String logo_url;

    //Constructor vacio
    public Marca() {}

    //Getters
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public String getPais_origen() {return pais_origen;}
    public String getLogo_url() {return logo_url;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPais_origen(String pais_origen) {this.pais_origen = pais_origen;}
    public void setLogo_url(String logo_url) {this.logo_url = logo_url;}
    
}
