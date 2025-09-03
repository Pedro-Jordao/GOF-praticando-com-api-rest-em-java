package br.com.dio.laboratorio_padroes_de_projeto_com_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LaboratorioPadroesDeProjetoComSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratorioPadroesDeProjetoComSpringApplication.class, args);
	}

}
