package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.mapper.CidadeMapperImpl;
import br.gov.mt.controladoria.scsp.model.Cidade;
import br.gov.mt.controladoria.scsp.model.dto.cidade.CidadeRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.cidade.CidadeResponseDTO;
import br.gov.mt.controladoria.scsp.service.CidadeService;
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
@RequestMapping("/cidades")
@Tag(name = "/cidades", description = "API para manipulação de Cidades")
public class CidadeResource {
	
	private final CidadeService cidadeService;
	private final CidadeMapperImpl mapper;


	@Operation(description = "API para salvar as cidades")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadeResponseDTO> criar(@Valid @RequestBody CidadeRequestDTO cidadeRequestDTO
	){
		Cidade cidade = mapper.toModel(cidadeRequestDTO);
		Cidade cidadeSalva = cidadeService.save(cidade);
		CidadeResponseDTO responseDTO = mapper.toResponse(cidadeSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para todas listar as cidades")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<CidadeResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<Cidade> cidades = cidadeService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<CidadeResponseDTO> cidadesDTO = cidades.map(mapper::toResponse);
		return ResponseEntity.ok(cidadesDTO);
	}

	@Operation(description = "API para pesquisar a cidades por codigo")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping("/{codigo}")
	public ResponseEntity<CidadeResponseDTO> buscarPeloCodigo(@PathVariable Integer codigo){
		Cidade cidade = cidadeService.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada."));
		CidadeResponseDTO responseDTO = mapper.toResponse(cidade);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para atualizar Cidades")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@PutMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadeResponseDTO> atualizar(@PathVariable Integer codigo,@Valid @RequestBody CidadeRequestDTO cidadeRequestDTO){
		Cidade cidade = mapper.toModel(cidadeRequestDTO);
		Cidade cidadeAtualizada = cidadeService.atualizar(codigo, cidade);
		CidadeResponseDTO responseDTO = mapper.toResponse(cidadeAtualizada);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para remover cidade")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.cidadeService.deleteById(codigo);
	}

}
