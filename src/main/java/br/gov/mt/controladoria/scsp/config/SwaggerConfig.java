package br.gov.mt.controladoria.scsp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define o esquema de segurança (Bearer Token)
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization"); // Nome do cabeçalho

        // Adiciona o esquema de segurança ao OpenAPI
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Controle de Servidor Público API")
                        .version("1.0")
                        .description("API de documentação para o Sistema de Controle de Servidor Público."))
                .schemaRequirement("Authorization", securityScheme) // Nome referenciado
                .addSecurityItem(new SecurityRequirement().addList("Authorization")); // Atribuir segurança
    }
}