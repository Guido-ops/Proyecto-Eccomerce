package eccomerce.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos para crear o actualizar un usuario")
public class UsuarioRequestDTO {

    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @Schema(description = "Correo electrónico único del usuario", example = "juan.perez@email.com")
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Size(max = 150)
    private String email;

    @Schema(description = "Contraseña del usuario (entre 8 y 200 caracteres)", example = "MiPassword123")
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 200, message = "La contraseña debe tener entre 8 y 200 caracteres")
    private String password;

    @Schema(description = "ID del rol asignado al usuario", example = "2")
    @NotNull(message = "El id del rol es obligatorio")
    private Long rolId;

    public UsuarioRequestDTO() {}

    public UsuarioRequestDTO(String nombre, String email, String password, Long rolId) {
        this.nombre   = nombre;
        this.email    = email;
        this.password = password;
        this.rolId    = rolId;
    }

    public String getNombre()   { return nombre; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public Long getRolId()      { return rolId; }

    public void setNombre(String nombre)     { this.nombre = nombre; }
    public void setEmail(String email)       { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRolId(Long rolId)         { this.rolId = rolId; }
}
