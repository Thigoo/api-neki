package br.com.api_neki.DTO;

public class LoginResDTO {

	private String token;
	private UsuarioResDTO usuario;

	public LoginResDTO() {
		super();
	}

	public LoginResDTO(String token, UsuarioResDTO usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioResDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResDTO usuario) {
		this.usuario = usuario;
	}

}
