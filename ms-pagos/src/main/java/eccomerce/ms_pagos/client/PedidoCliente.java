package eccomerce.ms_pagos.client;

import eccomerce.ms_pagos.dto.PedidoExternoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
 
@Component
public class PedidoCliente {
    
    private final RestTemplate restTemplate;
    private final String pedidosBaseUrl;
 
    public PedidoCliente(RestTemplate restTemplate,
                        @Value("${ms-pedidos.url}") String pedidosBaseUrl) {
        this.restTemplate   = restTemplate;
        this.pedidosBaseUrl = pedidosBaseUrl;
    }
 
    /**
     * Consulta un pedido por id en ms-pedidos.
     *
     * @throws RuntimeException si el pedido no existe, ms-pedidos
     *         responde un error, o no se puede establecer conexión.
     */
    public PedidoExternoDTO buscarPedido(Long pedidoId) {
        String url = pedidosBaseUrl + "/api/v1/pedidos/" + pedidoId;
 
        try {
            PedidoExternoDTO pedido = restTemplate.getForObject(url, PedidoExternoDTO.class);
 
            if (pedido == null) {
                throw new RuntimeException("ms-pedidos respondió vacío para el pedido " + pedidoId);
            }
 
            return pedido;
 
        } catch (HttpStatusCodeException ex) {
            // ms-pedidos SÍ respondió, pero con un código de error (404, 500, etc.)
            throw new RuntimeException("No se pudo validar el pedido " + pedidoId
                    + ". ms-pedidos respondió: " + ex.getStatusCode());
 
        } catch (ResourceAccessException ex) {
            // Nunca llegó respuesta — problema de red (servidor caído, timeout, etc.)
            throw new RuntimeException("ms-pagos no pudo conectarse con ms-pedidos en: " + url);
        }
    }

}
