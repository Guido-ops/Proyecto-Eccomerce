package eccomerce.ms_pedidos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Estado del ciclo de vida de un pedido")
public class EstadoPedidoDTO {

    @Schema(description = "ID del estado, generado por el sistema", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(description = "Nombre del estado", example = "PENDIENTE")
    @NotBlank(message = "El nombre del estado es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

    @Schema(description = "Descripción del estado y sus condiciones (opcional)", example = "Pedido registrado, pendiente de confirmación", nullable = true)
    @Size(max = 200, message = "La descripcion no puede superar los 200 caracteres")
    private String descripcion;

    public EstadoPedidoDTO() {}

    public EstadoPedidoDTO(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
