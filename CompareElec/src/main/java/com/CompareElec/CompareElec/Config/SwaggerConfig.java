package com.CompareElec.CompareElec.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /*@Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }*/

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                // accessToken이라는 스키마 만들어주기
                                .addSecuritySchemes("accessToken", new SecurityScheme()
                                        .name("accessToken")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                )
                                // refreshToken이라는 스키마 만들어주기
                                .addSecuritySchemes("refreshToken", new SecurityScheme()
                                        .name("refreshToken")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .bearerFormat("Bearer")
                                )
                );
    }


    private Info apiInfo() {
        return new Info()
                .title("BreadEnd Market API")
                .description("Specification")
                .version("1.0.0");
    }

}