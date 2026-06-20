package eccomerce.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Estructura estándar de respuesta cuando ocurre un error de negocio.
 * Se usa en los catches de los controllers en lugar de devolver Map.of("error", ...).
 */
@Schema(description = "Estructura de respuesta para errores de negocio")
public class ErrorResponseDTO {

    @Schema(description = "Mensaje descriptivo del error", example = "Estado no encontrado: 5")
    private String error;

    @Schema(description = "Momento en que ocurrió el error", example = "2026-06-20T14:30:00")
    private LocalDateTime timestamp;

    public ErrorResponseDTO() {}

    public ErrorResponseDTO(String error) {
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public String getError() { return error; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setError(String error) { this.error = error; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}