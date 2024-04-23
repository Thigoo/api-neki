package br.com.api_neki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_neki.DTO.HabilidadeUsuarioResDTO;
import br.com.api_neki.DTO.LoginReqDTO;
import br.com.api_neki.DTO.LoginResDTO;
import br.com.api_neki.DTO.UsuarioReqDTO;
import br.com.api_neki.DTO.UsuarioResDTO;
import br.com.api_neki.security.JWTService;
import br.com.api_neki.services.HabilidadeUsuarioService;
import br.com.api_neki.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private HabilidadeUsuarioService habilidadeUsuarioService;

	@Autowired
	private JWTService jwtService;

	@GetMapping
	public ResponseEntity<List<UsuarioResDTO>> buscarUsuarios() {
		return ResponseEntity.ok(usuarioService.buscarUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResDTO> buscarUsuarioPorId(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioResDTO> cadastrarUsuario(@RequestBody UsuarioReqDTO usuarioReq) {

		if (usuarioReq.getNome() == null) {
			throw new RuntimeException("Preencha o nome do usuário");
		}
		if (usuarioReq.getEmail() == null) {
			throw new RuntimeException("Preencha o campo email");
		}
		if (usuarioReq.getSenha() == null) {
			throw new RuntimeException("Preencha o campo senha");
		}

		return ResponseEntity.status(201).body(usuarioService.cadastrarUsuario(usuarioReq));

	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResDTO> atualizarUsuario(@PathVariable Long id,
			@RequestBody UsuarioReqDTO usuarioReq) {

		if (usuarioReq.getNome() == null) {
			throw new RuntimeException("Preencha o nome do usuário");
		}
		if (usuarioReq.getEmail() == null) {
			throw new RuntimeException("Preencha o campo email");
		}
		if (usuarioReq.getSenha() == null) {
			throw new RuntimeException("Preencha o campo senha");
		}

		return ResponseEntity.status(200).body(usuarioService.atualizarUsuario(id, usuarioReq));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {

		usuarioService.deletar(id);
		return ResponseEntity.status(204).build();

	}

	@PostMapping("/login")
	public ResponseEntity<LoginResDTO> logar(@RequestBody LoginReqDTO loginReq) {

		LoginResDTO usuarioRes = usuarioService.logar(loginReq);

		return ResponseEntity.status(200).body(usuarioRes);

	}

	@PostMapping("/validar/token")
	public ResponseEntity<String> validarToken(@RequestParam String token) {

		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}

		if (jwtService.validarToken(token)) {
			return ResponseEntity.ok("Token validado");
		} else {
			return ResponseEntity.ok("Token inválido ou expirado");
		}
	}
	
	@GetMapping("/habilidades_usuario")
	public ResponseEntity<List<HabilidadeUsuarioResDTO>> buscarHabilidadesUsuario(@RequestParam Long id) {
		return ResponseEntity.ok(habilidadeUsuarioService.buscarHabilidadesAssociadas(id));
	}
	
	@PostMapping("/cadastrar/habilidade")
	public ResponseEntity<HabilidadeUsuarioResDTO> cadastrarHabilidade(@RequestParam Long id_usuario, @RequestParam Long id_habilidade, Integer nivel) {
		
		return ResponseEntity.status(201).body(habilidadeUsuarioService.cadastrarHabilidade(id_usuario, id_habilidade, nivel));
		
	}

}






















