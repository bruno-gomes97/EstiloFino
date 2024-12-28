package br.com.dbc.vemser.estilofino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EstiloFinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstiloFinoApplication.class, args);
	}

}
