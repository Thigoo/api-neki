package br.com.api_neki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api_neki.DTO.HabilidadeReqDTO;
import br.com.api_neki.DTO.HabilidadeResDTO;
import br.com.api_neki.services.HabilidadeService;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {
	
	@Autowired
	private HabilidadeService habilidadeService;
	
	@GetMapping
	public List<HabilidadeResDTO> buscarHabilidades() {
		return habilidadeService.buscarHabilidades();
	}
	
	@GetMapping("/{id}")
	public HabilidadeResDTO buscarHabilidadePorId(@PathVariable Long id) {
		return habilidadeService.buscarHabilidadePorId(id);
	}
	
	@PostMapping("/cadastrar")
	public HabilidadeResDTO cadastrarHabilidade(@RequestBody HabilidadeReqDTO habilidadeReq) {
		return habilidadeService.cadastrarHabilidade(habilidadeReq);
	}

}
