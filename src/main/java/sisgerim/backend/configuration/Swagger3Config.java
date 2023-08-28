package sisgerim.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Swagger3Config {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .info(new Info()
                .title("SisGerim REST API")
                .description("Documentação da API da aplicação SisGerim")
                .version("1.0")
                .contact(new Contact()
                    .name("André Ricardo Morato Rosa")
                    .email("dedstads@gmail.com")
                    .url("https://github.com/dedstads")
                )
            );                                        
    }
}
