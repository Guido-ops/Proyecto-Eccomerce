package eccomerce.ms_inventario.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INVENTARIO")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_seq")
    @SequenceGenerator(name = "pago_seq", sequenceName = "PAGO_SEQ", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "PRODUCTO_ID", nullable = false, unique = true)
    private Long productoId;

    

    @NotNull
    @Column(name = "STOCK_ACTUAL", nullable = false)
    private Integer stockActual ;

    @Column(name = "STOCK_MINIMO", nullable = false)
    private Integer stockMinimo ;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientos = new ArrayList<>();

    //Constructor vacio
    public Inventario() {}

    //Getters
    public Long getId() {return id;}
    public Long getProductoId() {return productoId;}
    public Integer getStockActual() {return stockActual;}
    public Integer getStockMinimo() {return stockMinimo;}
    public List<MovimientoInventario> getMovimientos() {return movimientos;}


    //Setters
    public void setId(Long id) {this.id = id;}
    public void setProductoId(Long productoId) {this.productoId = productoId;}
    public void setStockActual(Integer stockActual) {this.stockActual = stockActual;}
    public void setStockMinimo(Integer stockMinimo) {this.stockMinimo = stockMinimo;}
    public void setMovimientos(List<MovimientoInventario> movimientos) {this.movimientos = movimientos;}


}

