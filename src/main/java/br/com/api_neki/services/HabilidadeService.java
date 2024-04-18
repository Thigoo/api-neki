package br.com.api_neki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api_neki.entities.Habilidade;
import br.com.api_neki.repositories.HabilidadeRepository;

@Service
public class HabilidadeService {
	
	@Autowired
	private HabilidadeRepository habilidadeRepository;
	
	public List<Habilidade> buscarHabilidades() {
		return habilidadeRepository.findAll();
	}
	
	public Habilidade buscarHabilidadePorId(Long id) {
		return habilidadeRepository.findById(id).get();
	}
	
	public Habilidade cadastrarHabilidade(Habilidade habilidade) {
		return habilidadeRepository.save(habilidade);
	}

}
