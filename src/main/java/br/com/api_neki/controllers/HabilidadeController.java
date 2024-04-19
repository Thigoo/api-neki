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
	public ResponseEntity<List<HabilidadeResDTO>> buscarHabilidades() {
		return ResponseEntity.ok(habilidadeService.buscarHabilidades());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HabilidadeResDTO> buscarHabilidadePorId(@PathVariable Long id) {
		return ResponseEntity.ok(habilidadeService.buscarHabilidadePorId(id));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<HabilidadeResDTO> cadastrarHabilidade(@RequestBody HabilidadeReqDTO habilidadeReq) {
		return ResponseEntity.status(201).body(habilidadeService.cadastrarHabilidade(habilidadeReq));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HabilidadeResDTO> atualizarHabilidade(@PathVariable Long id, @RequestBody HabilidadeReqDTO habilidadeReq) {
		return ResponseEntity.status(200).body(habilidadeService.atualizarHabilidades(id, habilidadeReq));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarHabilidade(@PathVariable Long id) {
		habilidadeService.deletarHabilidade(id);
		return  ResponseEntity.status(204).build();
	}

}








