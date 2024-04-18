package br.com.api_neki.DTO;
public class HabilidadeUsuarioReqDTO {

	private UsuarioResDTO usuario;
	private HabilidadeResDTO habilidade;
	private Integer nivel;

	public UsuarioResDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResDTO usuario) {
		this.usuario = usuario;
	}

	public HabilidadeResDTO getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(HabilidadeResDTO habilidade) {
		this.habilidade = habilidade;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

}
