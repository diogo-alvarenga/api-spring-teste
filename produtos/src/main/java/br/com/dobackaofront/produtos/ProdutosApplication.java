package br.com.dobackaofront.produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//a anotação diz que quem irá tomar conta do projeto é o spring
@SpringBootApplication
public class ProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutosApplication.class, args);
	}

}
