package br.com.api_neki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api_neki.entities.HabilidadeUsuario;

@Repository
public interface HabilidadeUsuarioRepository extends JpaRepository<HabilidadeUsuario, Long>{

}
