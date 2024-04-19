package br.com.api_neki.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api_neki.DTO.LoginResDTO;
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
	AuthenticationManager authManager;
	
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
	
	public UsuarioResDTO obterPorEmail(String email){
        Optional<Usuario> optUsuario =  usuarioRepository.findByEmail(email);

		if(optUsuario.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o email: " + email);
        }
        return modelMapper.map(optUsuario.get(),UsuarioResDTO.class);
    }

	public void uniqueEMAILeUSER(UsuarioReqDTO usuarioReq, Long id){
		List<UsuarioResDTO> listaUsuarioResponse = buscarUsuarios();

		for (UsuarioResDTO usuarioResponse : listaUsuarioResponse){
			if(usuarioResponse.getEmail().equals(usuarioReq.getEmail()) && usuarioResponse.getId() != id){
				throw new RuntimeException("E-mail já cadastrado!");
			}
			else if (usuarioResponse.getNome().equals(usuarioReq.getNome()) && usuarioResponse.getId() != id) {
				throw new RuntimeException("Nome de usuario já cadastrado!");
			}
		}		
		
	}
	
	public LoginResDTO logar(String email, String senha) {
		
		try {
			Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
			
			SecurityContextHolder
			.getContext()
			.setAuthentication(auth);
			
			String token = "Bearer " + jwtService.gerarToken(auth);
			
			UsuarioResDTO usuarioRes = obterPorEmail(email);
			
			return new LoginResDTO(token, usuarioRes);
			
		} catch(RuntimeException e) {
			throw new RuntimeException("E-mail ou senha incorretos.");
		}
	}
	
}















