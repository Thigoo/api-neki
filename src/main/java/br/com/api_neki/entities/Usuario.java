package br.com.api_neki.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<HabilidadeUsuario> habilidadesUsuario;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nome, String senha, List<HabilidadeUsuario> habilidadesUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.habilidadesUsuario = habilidadesUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<HabilidadeUsuario> getHabilidadesUsuario() {
		return habilidadesUsuario;
	}

	public void setHabilidadesUsuario(List<HabilidadeUsuario> habilidadesUsuario) {
		this.habilidadesUsuario = habilidadesUsuario;
	}

}
