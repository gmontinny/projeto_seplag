package br.gov.mt.controladoria.scsp.resource;


import br.gov.mt.controladoria.scsp.mapper.PessoaMapperImpl;
import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaResponseDTO;
import br.gov.mt.controladoria.scsp.service.PessoaService;
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
@RequestMapping("/pessoas")
@Tag(name = "/pessoas", description = "API para manipulação de Pessoas")
public class PessoaResource {

	private final PessoaService pessoaService;
	private final PessoaMapperImpl mapper;

	@Operation(description = "API para salvar as pessoas")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PessoaResponseDTO> criar(@Valid @RequestBody PessoaRequestDTO request){
		Pessoa pessoa = mapper.toModel(request);
		Pessoa pessoaSalva = pessoaService.save(pessoa);
		PessoaResponseDTO responseDTO = mapper.toResponse(pessoaSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para todas listar as pessoas")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<PessoaResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<Pessoa> pessoas = pessoaService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<PessoaResponseDTO> pessoasDTO = pessoas.map(mapper::toResponse);
		return ResponseEntity.ok(pessoasDTO);
	}


	@Operation(description = "API para atualizar Pessoas")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@PutMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PessoaResponseDTO> atualizar(@PathVariable Integer codigo,@Valid @RequestBody PessoaRequestDTO request){
		Pessoa pessoa = mapper.toModel(request);
		Pessoa pessoaAtualizada = pessoaService.atualizar(codigo, pessoa);
		PessoaResponseDTO responseDTO = mapper.toResponse(pessoaAtualizada);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para remover pessoa")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.pessoaService.deleteById(codigo);
	}
	
}
