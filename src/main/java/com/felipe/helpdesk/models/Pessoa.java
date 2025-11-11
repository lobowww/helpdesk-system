package com.felipe.helpdesk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipe.helpdesk.models.enums.Perfil;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //Anotação que indica que a classe Pessoa é uma entidade e que quero que uma tabela seja criada no banco, posso denominar a tabela caso queira '(name = "TB_PESSOA")'
public abstract class Pessoa implements Serializable{//Essa implementação do serializable serve para que seja criada uma sequência de bytes para que os dados consigam ser trafegados em rede
	private static final long serialVersionUID = 1L;
	
	@Id // Indorma que o atributo Id será o identificador da classe
	@GeneratedValue(strategy = GenerationType.IDENTITY)//A geração do valor de Id terá a estratégia de passar a responsabilidade para o banco, ou seja, não é responsabilidade da API, para cada objeto o banco gera um ID diferente
    protected Integer id;
    protected String nome;
    
    @Column(unique = true)//Coluna única no banco, ou seja, não terá 2 cpfs iguais no mesmo banco
    protected String cpf;
    
    @Column(unique = true)
    protected String email;
    protected String senha;
    
    @ElementCollection(fetch = FetchType.EAGER)//Informando que esta é uma coleção, e quando der um get a lista de perfis tem que vir imediatamente juntamente ao usuário, por conta das rotas que serão criadas.
    @CollectionTable(name = "PERFIS")//Informando que a tabela no banco será para uma coleção de dados e a nomea
    protected Set<Integer> perfis = new HashSet<>();
    
    @JsonFormat(pattern = "dd/MM/yyyy")//Informando o padrão da data
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().//Coloca a lista de perfis em uma "esteira", onde os dados passam um de cada vez
        map(x -> Perfil.toEnum(x)).//.map é uma operação de transformação, pega cada item da esteira e realiza a operação dentro do parenteses
        collect(Collectors.toSet());//Pega os novos itens e os agrupa em um Set ficando Ex.:[Perfil.ADMIN, Perfil.CLIENTE]
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());//Adiciona a lista de perfis o código do perfil
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    
    
}
