package eccomerce.ms_pagos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
 
import java.time.LocalDateTime;
 

@Schema(description = "Estructura de respuesta para errores de negocio")
public class ErrorResponseDTO {
 
    @Schema(description = "Mensaje descriptivo del error", example = "Ya existe un inventario para el producto: 5")
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
 