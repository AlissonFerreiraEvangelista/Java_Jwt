package br.com.jwt.jwt.swagger;

import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;



import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
   
    @Bean
    public Docket producerApi(){
        return new Docket(DocumentationType.SWAGGER_2)  
        .select()                                  
        .apis(RequestHandlerSelectors.any())              
        .paths(PathSelectors.any())                          
        .build()
        .apiInfo(metaInfo());

    }

    

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "JWT Api Rest",
                "Realizando autenticação com JWT",
                "1.0",
                "Terms of Service",
                new Contact("Alisson Ferreira", "https://github.com/AlissonFerreiraEvangelista",
                        "alisson.22559@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    } 
}
