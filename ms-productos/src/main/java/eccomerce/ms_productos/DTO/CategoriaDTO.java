package eccomerce.ms_productos.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO 5 - CategoriaDTO (Ambos)
 * Transporta los datos de una categoria al crearla (entrada) y al
 * mostrarla en el catalogo o en la ficha de un producto (salida).
 */
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(max = 30, message = "El nombre no puede superar los 30 caracteres")
    private String nombre;

    @Size(max = 200, message = "La descripcion no puede superar los 200 caracteres")
    private String descripcion;

    private boolean activa;

    public CategoriaDTO() {}

    public CategoriaDTO(Long id, String nombre, String descripcion, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activa = activa;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public boolean isActiva() { return activa; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
