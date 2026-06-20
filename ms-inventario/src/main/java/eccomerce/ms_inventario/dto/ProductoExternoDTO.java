package eccomerce.ms_inventario.dto;

public class ProductoExternoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private boolean activo;
 
    public ProductoExternoDTO() {}
 
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    public boolean isActivo() { return activo; }
 
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public void setActivo(boolean activo) { this.activo = activo; }

}
