package com.connectMentor.auth.domain.mentorado;


import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity // Indica que esta classe é uma entidade JPA
@Table(name="Mentor") // Especifica o nome da tabela no banco de dados
public class Mentor implements Serializable {
	private static final long serialVersionUID = 1L; // Número de versão da serialização

	@Id // Indica que este campo é a chave primária da entidade
	@GeneratedValue(strategy= GenerationType.IDENTITY) // Geração automática do valor do ID
	private Long idMentor; // ID do mentor

	// Demais atributos do mentor
	private String nome; // Nome do mentor
	private String cpf; // CPF do mentor
	private Integer genero; // Gênero do mentor (pode ser um código ou enum)
	private Date datanasc; // Data de nascimento do mentor
	private String senha; // Senha do mentor
	private String email; // E-mail do mentor
	private String telefone; // Número de telefone do mentor
	private String biografia; // Biografia do mentor
	@Lob
	private byte[] certificado; // Certificado do mentor (em formato de bytes)
	private byte[] foto; // Foto do mentor (em formato de bytes)

	// Construtor padrão vazio
	public Mentor() {
	}

	// Getters e Setters para os atributos da classe

	public Long getIdMentor() {
		return idMentor;
	}

	public void setIdMentor(Long idMentor) {
		this.idMentor = idMentor;
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

	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public byte[] getCertificado() {
		return certificado;
	}

	public void setCertificado(byte[] certificado) {
		this.certificado = certificado;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public void setCertificadoPath(String certificadoPath) {
	}
}
