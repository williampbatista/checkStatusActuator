package br.com.tarz.productionready;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductionReadyApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ProductionReadyApplication.class, args);
		
		SpringApplication app = new SpringApplication(ProductionReadyApplication.class);

		app.setRegisterShutdownHook(false);
//		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
//		CamelSpringBootApplicationController applicationController = applicationContext
//				.getBean(CamelSpringBootApplicationController.class);
//		applicationController.run();
		
		
	}

}
