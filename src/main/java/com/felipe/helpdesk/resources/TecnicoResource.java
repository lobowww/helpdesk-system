package com.felipe.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipe.helpdesk.domain.Tecnico;
import com.felipe.helpdesk.domain.dtos.TecnicoDTO;

import jakarta.validation.Valid;

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
	
	@GetMapping //Basta no momento da requisição lançar o /tecnicos e pronto, findAll feito
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){//@Valid -  Verifica se há validação nos dados, no caso os @NotNull / @RequestBody - Indica o corpo da requisição
		Tecnico newObj = service.create(objDTO);//Criação de um novo objeto Tecnico
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();//Aqui instanciamos um objeto URI pegamos seu valor para incluir no retorno
		return ResponseEntity.created(uri).build();//Para realizarmos a criação de um objeto o created pede uma URI, que é um acesso aquela informação como o /tecnico/1, para conseguir faça igual acima
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> upadate(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
		Tecnico obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
