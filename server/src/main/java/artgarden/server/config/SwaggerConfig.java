package artgarden.server.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "ArtGarden", description = "공연 정보 서비스", version = "1.0.0"), servers = {@Server(url="https://artgarden.site", description = "")})
@RequiredArgsConstructor
public class SwaggerConfig {



}
