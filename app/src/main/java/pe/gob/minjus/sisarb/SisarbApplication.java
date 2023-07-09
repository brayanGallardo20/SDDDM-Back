package pe.gob.minjus.sisarb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "pe.gob.minjus.sisarb" })
public class SisarbApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SisarbApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SisarbApplication.class);
	}

}
