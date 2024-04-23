package br.com.api_neki.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_neki.DTO.HabilidadeUsuarioResDTO;
import br.com.api_neki.DTO.UsuarioResDTO;
import br.com.api_neki.entities.Habilidade;
import br.com.api_neki.entities.HabilidadeUsuario;
import br.com.api_neki.entities.Usuario;
import br.com.api_neki.repositories.HabilidadeUsuarioRepository;

@Service
public class HabilidadeUsuarioService {
	
	@Autowired
	private HabilidadeUsuarioRepository habilidadeUsuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private HabilidadeService habilidadeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<HabilidadeUsuarioResDTO> buscarHabilidadesAssociadas(Long id_usuario) {
		
		UsuarioResDTO usuarioRes = usuarioService.buscarUsuarioPorId(id_usuario);
		
		List<HabilidadeUsuario> habilidadesUsuario = habilidadeUsuarioRepository.findByUsuario(modelMapper.map(usuarioRes, Usuario.class));
				
		return habilidadesUsuario
				.stream()
				.map(habUsu -> modelMapper.map(habUsu, HabilidadeUsuarioResDTO.class))
				.collect(Collectors.toList());
		
	}
	
	public HabilidadeUsuarioResDTO buscarHabPorId(Long id) {
		Optional<HabilidadeUsuario> optHabUsu = habilidadeUsuarioRepository.findById(id);
		
		if(optHabUsu.isEmpty()) {
			throw new RuntimeException("Nenhum registro encontrado para o id: " + id);
		}
		
		return modelMapper.map(optHabUsu.get(), HabilidadeUsuarioResDTO.class);
	}
	
	public HabilidadeUsuarioResDTO cadastrarHabilidade(Long id_usuario, Long id_habilidade, Integer nivel) {
		
		Usuario usuario = modelMapper.map(usuarioService.buscarUsuarioPorId(id_usuario), Usuario.class);
		Habilidade habilidade = modelMapper.map(habilidadeService.buscarHabilidadePorId(id_habilidade), Habilidade.class);
		
		HabilidadeUsuario habUsu = new HabilidadeUsuario();
		habUsu.setHabilidade(habilidade);
		habUsu.setUsuario(usuario);
		habUsu.setNivel(nivel);
		habUsu = habilidadeUsuarioRepository.save(habUsu);
		
		return modelMapper.map(habUsu, HabilidadeUsuarioResDTO.class);
				
	}

	public void deletarHabilidadeUsuario(Long id) {
		buscarHabPorId(id);
		habilidadeUsuarioRepository.deleteById(id);
	}
	
}


















