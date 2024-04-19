//package br.com.api_neki.services;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import br.com.api_neki.DTO.HabilidadeUsuarioResDTO;
//import br.com.api_neki.DTO.UsuarioResDTO;
//import br.com.api_neki.entities.HabilidadeUsuario;
//import br.com.api_neki.entities.Usuario;
//import br.com.api_neki.repositories.HabilidadeUsuarioRepository;
//
//@Service
//public class HabilidadeUsuarioService {
//	
//	@Autowired
//	private HabilidadeUsuarioRepository habilidadeUsuarioRepository;
//	
//	@Autowired
//	private UsuarioService usuarioService;
//	
//	@Autowired
//	private HabilidadeService habilidadeService;
//	
//	@Autowired
//	private ModelMapper modelMapper;
//	
//	public List<HabilidadeUsuarioResDTO> buscarHabilidadesAssociadas(Long id_usuario) {
//		
//		UsuarioResDTO usuarioRes = usuarioService.buscarUsuarioPorId(id_usuario);
//		List<HabilidadeUsuario> habilidadesUsuario = habilidadeUsuarioRepository.buscarUsuarios(modelMapper.map(usuarioRes, Usuario.class));
//		
//		return habilidadesUsuario
//				.stream()
//				.map(habUsu -> modelMapper.map(habilidadesUsuario, HabilidadeUsuarioResDTO.class))
//				.collect(Collectors.toList());
//		
//	}
//	
//	
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
