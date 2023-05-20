package br.com.peopleapi.peopleapi.common;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@RestController
@OpenAPIDefinition(info =
@Info(
    title = "Demo",
    version = "1.0",
    description = "Documentation for our people-api",
    license = @License(name = "Apache 2.0", url = "https://shre.ink/QsYH"),
    contact = @Contact(url = "https://shre.ink/QsYH", name = "Érico Gomes", email = "jerick.gs@gmail.com")
))
public class SwaggerConfig {

}
