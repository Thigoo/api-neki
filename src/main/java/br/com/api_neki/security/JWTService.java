package br.com.api_neki.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.api_neki.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {
	
	private static final String SECURITY_KEY = "S0m3R@nd0mK3y!F0rS3curity";
	
	public String gerarToken(Authentication auth) {
		
		int tempoExpiracao = 43200000; // 12 horas
		
		Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
		
		Usuario usuario = (Usuario) auth.getPrincipal();
		
		return Jwts
				.builder()
				.setSubject(usuario.getId().toString())
				.setIssuedAt(dataExpiracao)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
				.compact();
		
	}
	
	public boolean validarToken(String token) {
		
		try {
			Claims claims = Jwts
					.parser()
					.setSigningKey(SECURITY_KEY)
					.parseClaimsJws(token)
					.getBody();
			return true;
		} catch (ExpiredJwtException e) {
			System.out.println("Token expirado: " + e.getMessage());
			return false;
		} catch (JwtException e) {
			System.out.println("Erro na validação do token: " + e.getMessage());
			return false;
		}
		
	}
	
	public Optional<Long> buscarIdUsuario(String token) {
		
		try {
			Claims claims = Jwts
					.parser()
					.setSigningKey(SECURITY_KEY)
					.parseClaimsJws(token)
					.getBody();  
			return Optional.ofNullable(Long.parseLong(claims.getSubject()));
		} catch (Exception e) {
			return Optional.empty();
		}
		
	}

}
  














