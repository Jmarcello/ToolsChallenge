package br.com.phoenix.toolschallenge.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

		
	@Bean
	public Docket sgb2Api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.phoenix.toolschallenge")).paths(regex("/pagamento.*"))
				.build().apiInfo(metaInfo());

	}

	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Tools Challenge API Rest", "API Rest interface Phoenix Tools Challenge", "1.0.0 - SNAPSHOT", "Terms Of Service",
				new Contact("João Marcello Cardoso de Almeida", "joaomarcellocardoso@gmail.com", "joaomarcellocardoso@gmail.com"),
				"Apache License Version 2.0", "https://apache.org/licesen.html", new ArrayList<VendorExtension>());

		return apiInfo;
	}

}
