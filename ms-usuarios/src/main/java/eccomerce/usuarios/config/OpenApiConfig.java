package eccomerce.usuarios.config;

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
                        .title("MS Usuarios API")
                        .version("1.0")
                        .description("API REST del microservicio ms-usuarios.\n\n" +
                                     "Gestiona el registro de usuarios, sus roles y direcciones " +
                                     "de despacho del sistema de e-commerce."))
               
                        .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor local para desarrollo"));
    }
}