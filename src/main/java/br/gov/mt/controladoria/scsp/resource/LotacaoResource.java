package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.mapper.LotacaoMapperImpl;
import br.gov.mt.controladoria.scsp.model.Lotacao;
import br.gov.mt.controladoria.scsp.model.dto.lotacao.LotacaoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.lotacao.LotacaoResponseDTO;
import br.gov.mt.controladoria.scsp.service.LotacaoService;
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
@RequestMapping("/lotacoes")
@Tag(name = "/lotacoes", description = "API para manipulação de Lotações")
public class LotacaoResource {

	private final LotacaoService lotacaoService;
	private final LotacaoMapperImpl mapper;

	@Operation(description = "API para salvar as lotações")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LotacaoResponseDTO> criar(@Valid @RequestBody LotacaoRequestDTO request){
		Lotacao lotacao = mapper.toModel(request);
		Lotacao lotacaoSalva = lotacaoService.save(lotacao);
		LotacaoResponseDTO responseDTO = mapper.toResponse(lotacaoSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para listar todas as lotações")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<LotacaoResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<Lotacao> lotacaos = lotacaoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<LotacaoResponseDTO> lotacaoDTO = lotacaos.map(mapper::toResponse);
		return ResponseEntity.ok(lotacaoDTO);
	}


	@Operation(description = "API para atualizar Lotações")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@PutMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LotacaoResponseDTO> atualizar(@PathVariable Integer codigo, @Valid @RequestBody LotacaoRequestDTO request){
		Lotacao lotacao = mapper.toModel(request);
		Lotacao lotacaoAtualizada = lotacaoService.atualizar(codigo, lotacao);
		LotacaoResponseDTO responseDTO = mapper.toResponse(lotacaoAtualizada);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para remover lotação")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		lotacaoService.deleteById(codigo);
	}
}
