package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.mapper.ServidorTemporarioMapperImpl;
import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;
import br.gov.mt.controladoria.scsp.model.dto.servidorTemporario.ServidorTemporarioRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.servidorTemporario.ServidorTemporarioResponseDTO;
import br.gov.mt.controladoria.scsp.service.ServidorTemporarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servidorTemporario")
@Tag(name = "/servidorTemporario", description = "API para manipulação de Servidores Temporarios")
public class ServidorTemporarioResource {

	private final ServidorTemporarioService servidorTemporarioService;

	private final ServidorTemporarioMapperImpl mapper;

	@Operation(description = "API para listar todas os Servidores Temporarios")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<ServidorTemporarioResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<ServidorTemporario> servidorTemporarios = servidorTemporarioService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<ServidorTemporarioResponseDTO> servidorTemporarioResponseDTOS = servidorTemporarios.map(mapper::toResponse);
		return ResponseEntity.ok(servidorTemporarioResponseDTOS);
	}

	@Operation(description = "API para salvar os Servidores Temporarios")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServidorTemporarioResponseDTO> criar(@Valid @RequestBody ServidorTemporarioRequestDTO request){
		ServidorTemporario servidorTemporario = mapper.toModel(request);
		ServidorTemporario servidorTemporarioSalva = servidorTemporarioService.save(servidorTemporario);
		ServidorTemporarioResponseDTO responseDTO = mapper.toResponse(servidorTemporarioSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para atualizar Servidores Temporarios")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@PutMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServidorTemporarioResponseDTO> atualizar(@PathVariable Integer codigo,@Valid @RequestBody ServidorTemporarioRequestDTO request){
		ServidorTemporario servidorTemporario = mapper.toModel(request);
		ServidorTemporario servidorTemporarioAtualizada = servidorTemporarioService.atualizar(codigo, servidorTemporario);
		ServidorTemporarioResponseDTO responseDTO = mapper.toResponse(servidorTemporarioAtualizada);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para remover Servidor Temporario")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable ServidorTemporarioId id) {
		servidorTemporarioService.deletar(id);
	}


}
