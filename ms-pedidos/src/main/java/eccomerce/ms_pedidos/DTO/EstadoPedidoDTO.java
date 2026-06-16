package eccomerce.ms_pedidos.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO 9 - EstadoPedidoDTO (Ambos)
 * Transporta la informacion de una etapa del ciclo de vida de un pedido.
 * Como entrada permite al administrador crear nuevas etapas;
 * como salida informa al cliente en que etapa esta su pedido.
 */
public class EstadoPedidoDTO {

    private Long id;

    @NotBlank(message = "El nombre del estado es obligatorio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

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
