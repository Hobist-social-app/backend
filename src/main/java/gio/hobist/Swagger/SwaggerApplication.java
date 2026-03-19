package gio.hobist.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;

@OpenAPIDefinition(
        info=@Info(
              title="Hobist API",
              version="1.0",
              description = "API documentation"
        )
)
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
