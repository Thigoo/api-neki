package br.com.api_neki.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "habilidade")
public class Habilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String descricao;

	@OneToMany(mappedBy = "habilidade")
	private List<HabilidadeUsuario> habilidadesUsuario;

	public Habilidade() {
		super();
	}

	public Habilidade(Long id, String nome, String descricao,
			List<HabilidadeUsuario> habilidadesUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<HabilidadeUsuario> getHabilidadesUsuario() {
		return habilidadesUsuario;
	}

	public void setHabilidadesUsuario(List<HabilidadeUsuario> habilidadesUsuario) {
		this.habilidadesUsuario = habilidadesUsuario;
	}

}
