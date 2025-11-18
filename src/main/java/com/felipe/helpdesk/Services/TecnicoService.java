package com.felipe.helpdesk.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdesk.Services.exceptions.DataIntegrityViolationException;
import com.felipe.helpdesk.Services.exceptions.ObjectnotFoundException;
import com.felipe.helpdesk.domain.Pessoa;
import com.felipe.helpdesk.domain.Tecnico;
import com.felipe.helpdesk.domain.dtos.TecnicoDTO;
import com.felipe.helpdesk.repositories.PessoaRepository;
import com.felipe.helpdesk.repositories.TecnicoRepository;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
	
	//Injeta dependências
	@Autowired//Quando digo isso é praticamente que quando precisamos instanciar uma classe afim de atributos, construtor, ou métodos para ser utilizados em outra parte, tem que colocar o @Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoarepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);//O tipo de retorno indicado pelo JPA é o Optional
		//return obj.orElse(null); //orElse é a condicional de caso não tenha aquele técnico o retorno é nulo
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));//Senão será lançada uma exceção / () Função anônima recebe o objeto de exceção que leva uma mensagem
	}

	public List<Tecnico> findAll() {
		return repository.findAll();//Pega direto do banco de dados
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);//Deixo o id nulo, pois caso seja passado um id na requisição o banco vai entender que é um update e não é o que desejamos
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);//Como o acesso ao banco é indireto precisamos pensar nisso no momento de criar um novo obj / Lá em Tecnico será criado um novo construtor, que recebe o TecnicoDTO, com o padrão DTO para o processo reverso acontecer
		return repository.save(newObj);//Retorna o salvamento do novo objeto
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);//Pode ser passado na URL um id e no corpo da requisição outro id - isso gera uma falha de segurança, logo eu seto o valor do id do objDTO com o id
		Tecnico oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}
	
	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoarepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoarepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
}
