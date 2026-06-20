package eccomerce.ms_productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Categoría de producto — usada tanto en entrada como en salida")
public class CategoriaDTO {

    @Schema(description = "Id generado por el sistema", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre único de la categoría", example = "Electrónica")
    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(max = 30, message = "El nombre no puede superar los 30 caracteres")
    private String nombre;

    @Schema(description = "Descripción de la categoría (opcional)", example = "Dispositivos electrónicos y accesorios", nullable = true)
    @Size(max = 200, message = "La descripcion no puede superar los 200 caracteres")
    private String descripcion;

    @Schema(description = "Indica si la categoría está activa", example = "true")
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