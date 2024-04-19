package br.com.api_neki.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private CustomUserDetailsService userCustom;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = obterToken(request);
		Optional<Long> id = jwtService.buscarIdUsuario(token);

		if (id.isPresent()) {
			UserDetails usuario = userCustom.buscarUsuarioPorId(id.get());
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.getAuthorities());
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(auth);

		}
		
		filterChain.doFilter(request, response);

	}

	private String obterToken(HttpServletRequest http) {

		String token = http.getHeader("Authorization");
		if (token == null || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7);
	}

}
