package com.felipe.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipe.helpdesk.domain.Chamado;

import jakarta.validation.constraints.NotNull;

public class ChamadoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
	@NotNull(message = "O campo PRIORIDADE é requerido")
    private Integer prioridade;//O mesmo do tecnico serve para prioridade
	@NotNull(message = "O campo STATUS é requerido")
	private Integer status;//O mesmo do tecnico serve para status
	@NotNull(message = "O campo TITULO é requerido")
	private String titulo;
	@NotNull(message = "O campo OBSERVAÇÕES é requerido")
	private String observacoes;
	@NotNull(message = "O campo TECNICO é requerido")
	private Integer tecnico;//private Tecnico tecnico; -> Não precisamos de todas as informações do Tecnico no chamado, somente do id
	@NotNull(message = "O campo CLIENTE é requerido")
	private Integer cliente;//O mesmo do tecnico serve para o cliente
    private String nomeTecnico;// No chamado vamos ter uma coluna com o nome do Tecnico e o nome do Cliente, logo se eu quero pegar as informações
    //De um Tecnico eu faço um FindById do id do mesmo e puxo as informações e mostro na tabela. Só que imagine que eu tenho 50 chamados no meu banco
    //Tenho que fazer 1 FindById para cada chamado para pegar o nome do Tecnico, deixando nada permormático o nosso código. Para evitar isso vamos criar o nomeTecnico.
    private String nomeCliente;
    
	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado obj) {//Lembrar que quando criamos o Construtor ele deve receber entidade do Domain
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCodigo();
		this.status = obj.getStatus().getCodigo();
		this.titulo = obj.getTitulo();
		this.observacoes = obj.getObservacoes();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeTecnico = obj.getTecnico().getNome();
		this.nomeCliente = obj.getCliente().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
}
