package com.felipe.helpdesk.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdesk.domain.Tecnico;
import com.felipe.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	//Injeta dependências
	@Autowired//Quando digo isso é praticamente que quando precisamos instanciar uma classe afim de atributos, construtor, ou métodos para ser utilizados em outra parte, tem que colocar o @Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElse(null);
	}
}
