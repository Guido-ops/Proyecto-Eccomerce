package eccomerce.ms_productos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Marca de producto — usada tanto en entrada como en salida")
public class MarcaDTO {

    @Schema(description = "Id generado por el sistema", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre único de la marca", example = "Samsung")
    @NotBlank(message = "El nombre de la marca es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar los 80 caracteres")
    private String nombre;

    @Schema(description = "País de origen de la marca (opcional)", example = "Corea del Sur", nullable = true)
    @Size(max = 80, message = "El pais de origen no puede superar los 80 caracteres")
    private String paisOrigen;

    @Schema(description = "URL del logo de la marca (opcional)", example = "https://cdn.shopconnect.com/logos/samsung.png", nullable = true)
    @Size(max = 300, message = "El enlace al logo no puede superar los 300 caracteres")
    private String logoUrl;

    public MarcaDTO() {}

    public MarcaDTO(Long id, String nombre, String paisOrigen, String logoUrl) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.logoUrl = logoUrl;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPaisOrigen() { return paisOrigen; }
    public String getLogoUrl() { return logoUrl; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}