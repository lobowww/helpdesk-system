package com.felipe.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpDeskApplication{ //implements CommandLineRunner{
	
	/**
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;**/
	
	//Injeção de dependências(objetos)
	
	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}
	
	/**
	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Felipe Lobo", "72988909725", "felipe@email.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Sandro Lirio", "30613363892", "sandro@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		//Aqui criamos instâncias, mas não mandamos salvar no banco de dados
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}**/
}