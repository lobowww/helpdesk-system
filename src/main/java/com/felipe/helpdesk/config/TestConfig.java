package com.felipe.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipe.helpdesk.Services.DBService;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean// ou @PostConstruct
	public Boolean instanciaDB() {
		this.dbService.instanciaDB();
		return true;//Para funcionar o Bean precisa que retorne algo
		// Inicio a aplicação com o perfil "test" >> o spring vê e carrega o TestConfig >>
		// Checa que tem o @Bean chamado instanciaDB >> Para executar esse método, injeta o @Autowired primeiro >>
		// Spring executa instaciaDB >> Chama this.dbService.instanciaDB();
		// Seu banco de dados é populado com dados de teste para que seus testes automatizados ou seu ambiente de QA possam funcionar.
	}
}
