package eccomerce.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Rol de usuario en el sistema")
public class RolDTO {

    @Schema(description = "ID del rol, generado por el sistema", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(description = "Nombre identificador del rol", example = "ADMIN")
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 50)
    private String rolNombre;

    @Schema(description = "Descripción del rol y sus permisos", example = "Administrador con acceso total", nullable = true)
    @Size(max = 200)
    private String descripcion;

    public RolDTO() {}

    public RolDTO(Long id, String rolNombre, String descripcion) {
        this.id          = id;
        this.rolNombre   = rolNombre;
        this.descripcion = descripcion;
    }

    public Long getId()            { return id; }
    public String getRolNombre()      { return rolNombre; }
    public String getDescripcion() { return descripcion; }

    public void setId(Long id)                 { this.id = id; }
    public void setRolNombre(String rolNombre)       { this.rolNombre = rolNombre; }
    public void setDescripcion(String desc)    { this.descripcion = desc; }
}
