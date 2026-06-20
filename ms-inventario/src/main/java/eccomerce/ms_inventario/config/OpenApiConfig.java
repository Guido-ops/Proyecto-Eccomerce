package eccomerce.ms_inventario.config;


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
                        .title("MS Inventario API")
                        .version("1.0")
                        .description("API REST del microservicio ms-inventario.\n\n" +
                                     "Gestiona el stock de productos y sus movimientos (entradas, salidas, ajustes).\n\n" +
                                     "Antes de crear un inventario, este microservicio valida la existencia " +
                                     "del producto consultando a ms-productos vía RestTemplate."))

                        .addServersItem(new Server()
                        .url("http://localhost:8081")
                        .description("servidor local de desarrollo"));
    }

}
