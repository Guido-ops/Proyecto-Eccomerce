package eccomerce.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dirección de despacho de un usuario")
public class DireccionDTO {

    @Schema(description = "ID de la dirección, generado por el sistema", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(description = "Calle o dirección principal", example = "Av. Libertador 1234")
    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 200)
    private String calle;

    @Schema(description = "Ciudad de la dirección", example = "Santiago")
    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100)
    private String ciudad;

    @Schema(description = "Región o estado (opcional)", example = "Metropolitana", nullable = true)
    @Size(max = 80)
    private String region;

    public DireccionDTO() {}

    public DireccionDTO(Long id, String calle, String ciudad, String region) {
        this.id     = id;
        this.calle  = calle;
        this.ciudad = ciudad;
        this.region = region;
    }

    public Long getId()        { return id; }
    public String getCalle()   { return calle; }
    public String getCiudad()  { return ciudad; }
    public String getRegion()  { return region; }

    public void setId(Long id)           { this.id = id; }
    public void setCalle(String calle)   { this.calle = calle; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setRegion(String region) { this.region = region; }
}
