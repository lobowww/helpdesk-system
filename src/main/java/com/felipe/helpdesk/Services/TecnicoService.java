package com.felipe.helpdesk.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdesk.Services.exceptions.ObjectnotFoundException;
import com.felipe.helpdesk.domain.Tecnico;
import com.felipe.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
	
	//Injeta dependências
	@Autowired//Quando digo isso é praticamente que quando precisamos instanciar uma classe afim de atributos, construtor, ou métodos para ser utilizados em outra parte, tem que colocar o @Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);//O tipo de retorno indicado pelo JPA é o Optional
		//return obj.orElse(null); //orElse é a condicional de caso não tenha aquele técnico o retorno é nulo
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));//Senão será lançada uma exceção / () Função anônima recebe o objeto de exceção que leva uma mensagem
	}
}
