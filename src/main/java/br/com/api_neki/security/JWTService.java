package br.com.api_neki.security;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.api_neki.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private static final SecretKey SECURITY_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String gerarToken(Authentication auth) {
		
		try {
		
		int tempoExpiracao = 43200000; // 12 horas
		
		Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
		
		Usuario usuario = (Usuario) auth.getPrincipal();
		System.out.println("Gerando token para o usuário com ID: " + usuario.getId());
		
		String token = Jwts
				.builder()
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
				.compact();
        System.out.println("Token gerado com sucesso para o usuário com ID: " + usuario.getId());
        return token;
		} catch (Exception e) {
			System.err.println("Erro ao gerar token: " + e.getMessage());
	        throw new RuntimeException("Erro ao gerar token", e);
		}
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
  














