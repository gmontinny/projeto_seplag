package br.gov.mt.controladoria.scsp.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";

	private final JwtParser jwtParser;

	private final SecretKey secretKey;

	// A chave secreta será injetada do `application.yml`
	public JWTAuthorizationFilter(@Value("${jwt.secret-key}") String secretKey) {
		this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.jwtParser = Jwts.parserBuilder()
				.setSigningKey(this.secretKey)
				.build();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			String jwtToken = getJWTFromRequest(request);

			if (jwtToken != null) {
				Claims claims = validateToken(jwtToken);
				if (claims != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			}

			chain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			handleException(response, "Token expirado: " + e.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
		} catch (UnsupportedJwtException e) {
			handleException(response, "Formato de token não suportado: " + e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
		} catch (MalformedJwtException e) {
			handleException(response, "Token malformado: " + e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
		} catch (SecurityException | IllegalArgumentException e) {
			handleException(response, "Token inválido: " + e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private String getJWTFromRequest(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader != null && authenticationHeader.startsWith(PREFIX)) {
			return authenticationHeader.replace(PREFIX, "");
		}
		return null;
	}

	private Claims validateToken(String token) {
		return jwtParser.parseClaimsJws(token).getBody();
	}

	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				claims.getSubject(),
				null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
		);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private void handleException(HttpServletResponse response, String message, int statusCode) throws IOException {
		response.setStatus(statusCode);
		response.setContentType("application/json");
		response.getWriter().write("{\"error\": \"" + message + "\", \"status\": " + statusCode + "}");
	}
}