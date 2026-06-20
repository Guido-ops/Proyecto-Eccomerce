package eccomerce.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Datos de un usuario registrado en el sistema")
public class UsuarioResponseDTO {

    @Schema(description = "ID del usuario", example = "10")
    private Long id;

    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String nombre;

    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@email.com")
    private String email;

    @Schema(description = "Indica si el usuario está activo", example = "true")
    private Boolean activo;

    @Schema(description = "Rol asignado al usuario")
    private RolDTO rol;

    @Schema(description = "Direcciones de despacho registradas del usuario")
    private List<DireccionDTO> direcciones;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Long id, String nombre, String email,
                             Boolean activo, RolDTO rol, List<DireccionDTO> direcciones) {
        this.id          = id;
        this.nombre      = nombre;
        this.email       = email;
        this.activo      = activo;
        this.rol         = rol;
        this.direcciones = direcciones;
    }

    public Long getId()                        { return id; }
    public String getNombre()                  { return nombre; }
    public String getEmail()                   { return email; }
    public Boolean getActivo()                 { return activo; }
    public RolDTO getRol()                     { return rol; }
    public List<DireccionDTO> getDirecciones() { return direcciones; }

    public void setId(Long id)                                 { this.id = id; }
    public void setNombre(String nombre)                       { this.nombre = nombre; }
    public void setEmail(String email)                         { this.email = email; }
    public void setActivo(Boolean activo)                      { this.activo = activo; }
    public void setRol(RolDTO rol)                             { this.rol = rol; }
    public void setDirecciones(List<DireccionDTO> direcciones) { this.direcciones = direcciones; }
}
