package br.gov.mt.controladoria.scsp.resource;


import br.gov.mt.controladoria.scsp.mapper.UnidadeEnderecoMapperImpl;
import br.gov.mt.controladoria.scsp.model.UnidadeEndereco;
import br.gov.mt.controladoria.scsp.model.dto.unidadeEndereco.UnidadeEnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.unidadeEndereco.UnidadeEnderecoResponseDTO;
import br.gov.mt.controladoria.scsp.service.UnidadeEnderecoService;
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
@RequestMapping("/unidadeEnderecos")
@Tag(name = "/unidadeEnderecos", description = "API para manipulação de Unidade Endereço")
public class UnidadeEnderecoResource {

	private final UnidadeEnderecoMapperImpl mapper;
	private final UnidadeEnderecoService unidadeEnderecoService;


	@Operation(description = "API para listar todas as unidades endereços")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<UnidadeEnderecoResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<UnidadeEndereco> unidadeEnderecos = unidadeEnderecoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<UnidadeEnderecoResponseDTO> unidadeEnderecoDTO = unidadeEnderecos.map(mapper::toResponse);
		return ResponseEntity.ok(unidadeEnderecoDTO);
	}

	@Operation(description = "API para salvar as unidades endereços")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UnidadeEnderecoResponseDTO> criar(@Valid @RequestBody UnidadeEnderecoRequestDTO request){
		UnidadeEndereco unidadeEndereco = mapper.toModel(request);
		UnidadeEndereco unidadeEnderecoSalva = unidadeEnderecoService.save(unidadeEndereco);
		UnidadeEnderecoResponseDTO responseDTO = mapper.toResponse(unidadeEnderecoSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para remover Unidade Endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "unidadeId", in = ParameterIn.PATH), @Parameter(name = "enderecoId", in = ParameterIn.PATH)})
	@DeleteMapping("/unidade/{unidadeId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer unidadeId, @PathVariable Integer enderecoId) {		
		unidadeEnderecoService.remover(unidadeId, enderecoId);
	}

	@Operation(description = "API para atualizar Unidade Endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "unidadeId", in = ParameterIn.PATH), @Parameter(name = "enderecoId", in = ParameterIn.PATH)})
	@PutMapping(value = "/unidade/{unidadeId}/endereco/{enderecoId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<UnidadeEnderecoResponseDTO> atualizar(@PathVariable Integer unidadeId, @PathVariable Integer enderecoId,@Valid @RequestBody UnidadeEnderecoRequestDTO request) {
		UnidadeEndereco unidadeEndereco = mapper.toModel(request);
		UnidadeEndereco unidadeEnderecoAtualizada = unidadeEnderecoService.atualizar(unidadeId,enderecoId, unidadeEndereco);
		UnidadeEnderecoResponseDTO responseDTO = mapper.toResponse(unidadeEnderecoAtualizada);
		return ResponseEntity.ok(responseDTO);
	}
}
