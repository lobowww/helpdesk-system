package com.felipe.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.helpdesk.domain.Tecnico;
import com.felipe.helpdesk.domain.dtos.TecnicoDTO;
import com.felipe.helpdesk.Services.TecnicoService;

@RestController//Controlador Rest
@RequestMapping(value = "/tecnicos")//Para adicionar o endpoint inicial para os serviços/ Para acessar um determinado recurso dos tecnicos eu acesso com o endpoint inicial com o valor em seguida
public class TecnicoResource {
	// localhost:8080/tecnicos
	
	@Autowired
	private TecnicoService service;
	
	//ANTES DA CRIAÇÃO DO DTO
	// O que é o ResponseEntity? Queremos representar toda resposta http, é utilizado em APIs como boa prática, vem com diversas funções e trabalha com segurança da informação.
	//@GetMapping(value = "/{id}")// Quando eu acessar localhost:8080/tecnicos/1 -> Eu puxo o técnico pelo id dele pelo path
	//public ResponseEntity<Tecnico> findById(@PathVariable Integer id){// Como estou recebendo uma variável de path tenho que informar dentro do método que estou recebendo um @PathVariable|Qual o tipo primitivo|nome
	//	Tecnico obj = service.findById(id);//Declaro uma variável do tipo Tecnico chamada obj que recebe o valor do serviço com o método findById(que recebe o id)
	//	return ResponseEntity.ok().body(obj);
	//}
	
	//Após criar o DTO
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){//A alteração de Tecnico para TecnicoDTO trouxe um problema, o obj no retorno é do tipo Tecnico
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));//Invés do obj eu pego meu DTO, lembra que o construtor recebe um objeto Tecnico? Entrega o obj para esse construtor
	}
}
