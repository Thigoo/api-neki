package br.com.api_neki.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_neki.DTO.HabilidadeReqDTO;
import br.com.api_neki.DTO.HabilidadeResDTO;
import br.com.api_neki.entities.Habilidade;
import br.com.api_neki.repositories.HabilidadeRepository;

@Service
public class HabilidadeService {

	@Autowired
	private HabilidadeRepository habilidadeRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<HabilidadeResDTO> buscarHabilidades() {
		List<Habilidade> habilidades = habilidadeRepository.findAll();

		return habilidades.stream().map(hab -> modelMapper.map(hab, HabilidadeResDTO.class))
				.collect(Collectors.toList());
	}

	public HabilidadeResDTO buscarHabilidadePorId(Long id) {
		Optional<Habilidade> optHabilidade = habilidadeRepository.findById(id);

		if (optHabilidade.isEmpty()) {
			throw new RuntimeException("Nenhuma habilidade encontrada para o id " + id);
		}

		return modelMapper.map(optHabilidade.get(), HabilidadeResDTO.class);

	}

	public HabilidadeResDTO cadastrarHabilidade(HabilidadeReqDTO habilidadeReq) {

		Habilidade habilidadeModelo = modelMapper.map(habilidadeReq, Habilidade.class);

		habilidadeModelo = habilidadeRepository.save(habilidadeModelo);

		return modelMapper.map(habilidadeModelo, HabilidadeResDTO.class);
	}

	public HabilidadeResDTO atualizarHabilidades(Long id, HabilidadeReqDTO habilidadeReq) {

		buscarHabilidadePorId(id);
		
		Habilidade habilidadeModelo = modelMapper.map(habilidadeReq, Habilidade.class);
		
		habilidadeModelo.setId(id);
		habilidadeModelo = habilidadeRepository.save(habilidadeModelo);
		
		return modelMapper.map(habilidadeModelo, HabilidadeResDTO.class);
	}
	
	public void deletarHabilidade(Long id) {
		buscarHabilidadePorId(id);
		habilidadeRepository.deleteById(id);
	}
}











