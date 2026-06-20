package eccomerce.ms_inventario.client;

import eccomerce.ms_inventario.dto.ProductoExternoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoCliente {
 

    private final RestTemplate restTemplate;
    private final String productosBaseUrl;
 
    public ProductoCliente(RestTemplate restTemplate,
                          @Value("${ms-productos.url}") String productosBaseUrl) {
        this.restTemplate     = restTemplate;
        this.productosBaseUrl = productosBaseUrl;
    }
 
    /**
     * Consulta un producto por id en ms-productos.
     *
     * @throws RuntimeException si el producto no existe, ms-productos
     *         responde un error, o no se puede establecer conexión.
     */
    public ProductoExternoDTO buscarProducto(Long productoId) {
        String url = productosBaseUrl + "/api/v1/productos/" + productoId;
 
        try {
            ProductoExternoDTO producto = restTemplate.getForObject(url, ProductoExternoDTO.class);
 
            if (producto == null) {
                throw new RuntimeException("ms-productos respondió vacío para el producto " + productoId);
            }
 
            return producto;
 
        } catch (HttpStatusCodeException ex) {
            // ms-productos SÍ respondió, pero con un código de error (404, 500, etc.)
            throw new RuntimeException("No se pudo validar el producto " + productoId
                    + ". ms-productos respondió: " + ex.getStatusCode());
 
        } catch (ResourceAccessException ex) {
            // Nunca llegó respuesta — problema de red (servidor caído, timeout, etc.)
            throw new RuntimeException("ms-inventario no pudo conectarse con ms-productos en: " + url);
        }
    }
}
