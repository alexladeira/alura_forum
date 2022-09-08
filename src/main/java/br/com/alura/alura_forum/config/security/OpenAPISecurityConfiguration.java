package br.com.alura.alura_forum.config.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Forum API", version = "${api.version}"))
public class OpenAPISecurityConfiguration {
}
