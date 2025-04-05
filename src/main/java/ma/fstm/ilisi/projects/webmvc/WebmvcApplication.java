package ma.fstm.ilisi.projects.webmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebmvcApplication extends SpringBootServletInitializer implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(WebmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello!!!!!");
		System.out.println("To access the application in guithb: https://github.com/omarelbeggar/WebMVCRepo");
	}

}