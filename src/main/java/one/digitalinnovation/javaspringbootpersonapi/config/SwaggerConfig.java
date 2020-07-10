package one.digitalinnovation.javaspringbootpersonapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("one.digitalinnovation.javaspringbootpersonapi"))
                .paths(regex("/api/v1.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Reprodução do projeto desenvolvido na plataforma Digital Innovation One",
                "API REST para o gerenciamento de senhas para uma pequena empresa utilizando Java e Spring Boot.",
                "1.0",
                "Terms of Service",
                new Contact(
                        "Willian Silva",
                        "https://github.com/Willianf-Silva",
                        "willian.ferreira.da.silva@gmail.com"),
                "MIT License",
                "https://mit-license.org/", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
