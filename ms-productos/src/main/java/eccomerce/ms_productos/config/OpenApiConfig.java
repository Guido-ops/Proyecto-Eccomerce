package eccomerce.ms_productos.config;

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
                        .title("MS Productos API")
                        .version("1.0")
                        .description("API REST del microservicio ms-productos.\n\n" +
                                     "Gestiona el catálogo de productos del sistema de e-commerce: " +
                                     "categorías, marcas, productos e imágenes.\n\n" +
                                     "Este microservicio expone el endpoint GET /api/v1/productos/{id} " +
                                     "que es consumido por ms-inventario vía RestTemplate para validar " +
                                     "la existencia de un producto antes de crear su registro de inventario."))
                    
                .addServersItem(new Server()
                            .url("http://localhost:8084")
                            .description("Servidor local para desarrollo"));
    } 
}
