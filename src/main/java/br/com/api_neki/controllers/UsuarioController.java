package br.com.api_neki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_neki.entities.Usuario;
import br.com.api_neki.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> buscarUsuarios() {
		return usuarioService.buscarUsuarios();
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario buscarUsuarioPorId(@PathVariable Long id) {
		return usuarioService.buscarUsuarioPorId(id);
	}
	
	@PostMapping("/cadastrar")
	public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.cadastrarUsuario(usuario);
	}

}
