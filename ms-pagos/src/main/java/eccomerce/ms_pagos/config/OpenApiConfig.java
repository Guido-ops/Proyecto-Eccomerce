package eccomerce.ms_pagos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS Pagos API")
                        .version("1.0")
                        .description("API REST del microservicio ms-pagos.\n\n" +
                                     "Gestiona el procesamiento de pagos, los métodos de pago disponibles " +
                                     "y el historial de transacciones de cada pago.\n\n" +
                                     "Antes de procesar un pago, este microservicio valida que el pedido " +
                                     "exista y esté en estado CONFIRMADO, consultando a ms-pedidos vía RestTemplate."))
                    
                        .addServersItem(new Server()   
                        .url("http://localhost:8082")
                        .description("servidor local de desarrollo"));
    }

}

