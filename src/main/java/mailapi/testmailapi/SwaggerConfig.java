package mailapi.testmailapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI SwaggerApi(){
        var info = new Info()
                .title("Mail API")
                .description("Java + Spring framework test task for MediaSoft")
                .version("1.0");

        return new OpenAPI().info(info);
    }
}
