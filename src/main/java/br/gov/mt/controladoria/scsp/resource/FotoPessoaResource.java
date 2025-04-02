package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.mapper.FotoPessoaMapperImpl;
import br.gov.mt.controladoria.scsp.model.FotoPessoa;
import br.gov.mt.controladoria.scsp.model.dto.fotoPessoa.FotoPessoaRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.fotoPessoa.FotoPessoaResponseDTO;
import br.gov.mt.controladoria.scsp.service.FotoPessoaService;
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
@RequestMapping("/fotoPessoas")
@Tag(name = "/fotoPessoas", description = "API para manipulação de Fotos Pessoas")
public class FotoPessoaResource {

	private final FotoPessoaService fotoPessoaService;
	private final FotoPessoaMapperImpl mapper;

	@Operation(description = "API para salvar as foto pessoa")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FotoPessoaResponseDTO> criar(@Valid @RequestBody FotoPessoaRequestDTO request){
		FotoPessoa fotoPessoa = mapper.toModel(request);
		FotoPessoa fotoPessoaSalva = fotoPessoaService.save(fotoPessoa);
		FotoPessoaResponseDTO responseDTO = mapper.toResponse(fotoPessoaSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para todas listar as Fotos Pessoas")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<FotoPessoaResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<FotoPessoa> fotoPessoas = fotoPessoaService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<FotoPessoaResponseDTO> fotoPessoasDTO = fotoPessoas.map(mapper::toResponse);
		return ResponseEntity.ok(fotoPessoasDTO);
	}


	@Operation(description = "API para atualizar Foto Pessoas")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@PutMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FotoPessoaResponseDTO> atualizar(@PathVariable Integer codigo,@Valid @RequestBody FotoPessoaRequestDTO request){
		FotoPessoa fotoPessoa = mapper.toModel(request);
		FotoPessoa fotoPessoaAtualizada = fotoPessoaService.atualizar(codigo, fotoPessoa);
		FotoPessoaResponseDTO responseDTO = mapper.toResponse(fotoPessoaAtualizada);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para remover foto pessoa")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		fotoPessoaService.deleteById(codigo);
	}
}
