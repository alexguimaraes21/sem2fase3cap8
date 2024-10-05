package br.com.fiap.atvcap8.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration
{
    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("FIAP 2ano- DEVOPS")
                        .description("Atividade cap. 06 - fase 06")
                        .contact(new Contact()
                                .name("FIAP")
                                .email("fiap.dev@gmail.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://fiap_devops/api/license")
                        )
                );
    }
}