package blog.scarycoders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RoleBasedLoginApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RoleBasedLoginApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RoleBasedLoginApplication.class, args);
	}
}
