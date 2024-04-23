package br.com.api_neki.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "habilidade_usuario")
public class HabilidadeUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer nivel;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_habilidade")
	private Habilidade habilidade;

	public HabilidadeUsuario() {
		super();
	}

	public HabilidadeUsuario(Long id, Usuario usuario, Integer nivel, Habilidade habilidade) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nivel = nivel;
		this.habilidade = habilidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Habilidade getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}

}
