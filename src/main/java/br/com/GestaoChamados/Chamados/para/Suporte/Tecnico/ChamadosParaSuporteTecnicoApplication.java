package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChamadosParaSuporteTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChamadosParaSuporteTecnicoApplication.class, args);
	}

}
