package com.example.tiendaGafas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // Excluimos la configuración de la base de datos
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
