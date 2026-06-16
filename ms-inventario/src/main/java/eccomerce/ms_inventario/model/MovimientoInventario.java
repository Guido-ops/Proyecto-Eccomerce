package eccomerce.ms_inventario.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "MOVIMIENTO_INVENTARIO")
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_seq")
    @SequenceGenerator(name = "pago_seq", sequenceName = "PAGO_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String tipo;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false) //le agregamos fecha para que se vea de mejor manera el inventario
    private LocalDateTime fecha;
    
    @PrePersist 
    protected void onCreate() { this.fecha = LocalDateTime.now();   }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVENTARIO_ID", nullable = false)
    @JsonIgnore
    private Inventario inventario;

    //Constructor vacio
    public MovimientoInventario() {}

    //Getters
    public Long getId() {return id;}
    public String getTipo() {return tipo;}
    public Integer getCantidad() {return cantidad;}
    public Inventario getInventario() {return inventario;}
    public LocalDateTime getFecha() {return fecha;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}
    public void setInventario(Inventario inventario) {this.inventario = inventario;}
    public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}
}
