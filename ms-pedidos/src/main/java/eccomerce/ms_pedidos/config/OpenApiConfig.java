package eccomerce.ms_pedidos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("MS Pedidos API")
                .version("1.0")
                .description("API REST del microservicio ms-pedidos.\n\n" +
                    "Gestiona el ciclo de vida completo de los pedidos: creación, " +
                    "estados (PENDIENTE, CONFIRMADO, ENTREGADO, etc.) y detalle de artículos.\n\n" +
                    "Este microservicio es consultado por ms-pagos vía RestTemplate, " +
                    "que valida el estado del pedido antes de procesar un pago."))
                
                .addServersItem(new Server()
                .url("http://localhost:8083")
                .description("Servidor local de desarrollo"));
    }
}