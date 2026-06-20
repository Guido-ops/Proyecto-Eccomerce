package eccomerce.ms_pedidos.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
    @SequenceGenerator(name = "cat_seq", sequenceName = "CAT_SEQ", allocationSize = 1)
    private Long id;
    
    @Column(name = "FECHA_PEDIDO", nullable = false)
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @NotNull
    @Positive(message = "El total del pedido debe ser mayor a cero")
    @Column(nullable = false)
    private Double total;

    @NotNull(message = "El Id del usuario es obligatoriio")
    @Column(name = "USUARIO_ID", nullable = false)
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTADO_ID", nullable = false)
    private EstadoPedido estado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    //Constructor vacio
    public Pedido() {}

    //Getters
    public Long getId() {return id;}
    public LocalDateTime getFechaPedido() {return fechaPedido;}
    public Double getTotal() {return total;}
    public Long getUsuarioId() {return usuarioId;}
    public EstadoPedido getEstado() {return estado;}
    public List<DetallePedido> getDetalles() {return detalles;}

    //Setters
    public void setId(Long id) {this.id = id;}
    public void setFechaPedido(LocalDateTime fechaPedido) {this.fechaPedido = fechaPedido;}
    public void setTotal(Double total) {this.total = total;}
    public void setUsuarioId(Long usuarioId) {this.usuarioId = usuarioId;}
    public void setEstado(EstadoPedido estado) {this.estado = estado;}
    public void setDetalles(List<DetallePedido> detalles) {this.detalles = detalles;}
}
