package br.com.api_neki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_neki.entities.Usuario;
import br.com.api_neki.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> buscarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
}
