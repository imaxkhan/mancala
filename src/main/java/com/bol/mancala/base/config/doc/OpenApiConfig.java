package com.bol.mancala.base.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * customize spring doc interface (swagger)
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Mancal Service")
                        .description("Responsible For Handling Mancala Board Game")
                        .version("v0.0.1")
                        .license(new License().name("bol.com").url("https://bol.com")));
    }
}
