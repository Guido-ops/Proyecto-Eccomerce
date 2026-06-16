package com.eccomerce.usuarios.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class DireccionDTO {

    // Solo presente en salida — el sistema lo genera con la secuencia CAT_SEQ
    private Long id;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 200)
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100)
    private String ciudad;

    @Size(max = 80)
    private String region; // opcional

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
