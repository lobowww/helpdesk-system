package com.felipe.helpdesk.Services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.helpdesk.domain.Chamado;
import com.felipe.helpdesk.domain.Cliente;
import com.felipe.helpdesk.domain.Tecnico;
import com.felipe.helpdesk.domain.enums.Perfil;
import com.felipe.helpdesk.domain.enums.Prioridade;
import com.felipe.helpdesk.domain.enums.Status;
import com.felipe.helpdesk.repositories.ChamadoRepository;
import com.felipe.helpdesk.repositories.ClienteRepository;
import com.felipe.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Felipe Lobo", "72988909725", "felipe@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "Rapha", "69887661899", "rapha@email.com", "123");
		tec2.addPerfil(Perfil.ADMIN);
		
		Tecnico tec3 = new Tecnico(null, "Anso", "08825947372", "anso@email.com", "123");
		tec3.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Sandro Lirio", "42121575359", "sandro@email.com", "123");
		
		Cliente cli2 = new Cliente(null, "Fernando", "39499085955", "fernando@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 02", "Segundo chamado", tec3, cli2);
		
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 03", "Terceiro chamado", tec2, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		tecnicoRepository.saveAll(Arrays.asList(tec2));
		tecnicoRepository.saveAll(Arrays.asList(tec3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		clienteRepository.saveAll(Arrays.asList(cli2));
		chamadoRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(c2));
		chamadoRepository.saveAll(Arrays.asList(c3));
	}
}