package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.resource.dto.UserDto;
import br.gov.mt.controladoria.scsp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserResource {

	private final UserService service;

	@PostMapping("/login")
	public UserDto login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		String token = service.getJWTToken(username);
		return new UserDto(username, null, token); // pwd pode ser null
	}

	@PostMapping("/refresh-token")
	public UserDto refreshToken(@RequestParam("token") String token) {
		// Valida o token atual
		if (service.isTokenValid(token)) {
			String username = service.getUsernameFromToken(token); // Obtém o username do token invalido
			String newToken = service.getJWTToken(username); // Cria um novo token
			return new UserDto(username, null, newToken); // pwd será null
		} else {
			throw new IllegalArgumentException("Token inválido ou expirado.");
		}
	}
}