package br.ufscar.dc.dsw.SiteConsultas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SiteConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteConsultasApplication.class, args);
	}

}
