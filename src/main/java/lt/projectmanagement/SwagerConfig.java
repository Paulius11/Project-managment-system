package lt.projectmanagement;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SwagerConfig {
	@Bean
	public Docket swaggerDocket() {

		return new Docket(DocumentationType.SWAGGER_2)

				.apiInfo(apiInfo()).select()

				.apis(RequestHandlerSelectors.basePackage("lt.projectmanagement"))

				.build();

	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("Project management").version("0.0.1-SNAPSHOT").build();
	}

}