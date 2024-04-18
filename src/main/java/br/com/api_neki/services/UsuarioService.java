package br.com.api_neki.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api_neki.DTO.UsuarioReqDTO;
import br.com.api_neki.DTO.UsuarioResDTO;
import br.com.api_neki.entities.Usuario;
import br.com.api_neki.repositories.UsuarioRepository;
import br.com.api_neki.security.JWTService;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	AuthenticationManager authMAnager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<UsuarioResDTO> buscarUsuarios() {
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return usuarios
				.stream()
				.map(usu -> modelMapper.map(usu, UsuarioResDTO.class))
				.collect(Collectors.toList());
	}
	
	public UsuarioResDTO buscarUsuarioPorId(Long id) {
		
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		
		if(optUsuario.isEmpty()) {
			throw new RuntimeException("Nenhum usuário cadastrado no ID " + id);		
			
		}
		
		return modelMapper.map(optUsuario.get(), UsuarioResDTO.class);
		
	}
	
	public UsuarioResDTO cadastrarUsuario(UsuarioReqDTO usuarioReq) {

		Usuario usuarioModelo = modelMapper.map(usuarioReq, Usuario.class);
		
		String senha = passwordEncoder.encode(usuarioModelo.getSenha());
		usuarioModelo.setSenha(senha);
		usuarioModelo = usuarioRepository.save(usuarioModelo);
		
		return modelMapper.map(usuarioModelo, UsuarioResDTO.class);
		
	}
	
	public UsuarioResDTO atualizarUsuario(Long id, UsuarioReqDTO usuarioReq) {
		
		buscarUsuarioPorId(id);
		
		Usuario usuarioModelo = modelMapper.map(usuarioReq, Usuario.class);
		usuarioModelo.setId(id);
		
		String senha = passwordEncoder.encode(usuarioModelo.getSenha());
		usuarioModelo.setSenha(senha);
		usuarioModelo = usuarioRepository.save(usuarioModelo);
		
		return modelMapper.map(usuarioModelo, UsuarioResDTO.class);
		
	}
	
	public void deletar(Long id) {
		
		buscarUsuarioPorId(id);		
		usuarioRepository.deleteById(id);
		
	}
	
	
}















