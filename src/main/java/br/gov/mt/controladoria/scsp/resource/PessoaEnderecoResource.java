package br.gov.mt.controladoria.scsp.resource;


import br.gov.mt.controladoria.scsp.mapper.PessoaEnderecoMapperImpl;
import br.gov.mt.controladoria.scsp.model.PessoaEndereco;
import br.gov.mt.controladoria.scsp.model.dto.pessoaEndereco.PessoaEnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.pessoaEndereco.PessoaEnderecoResponseDTO;
import br.gov.mt.controladoria.scsp.service.PessoaEnderecoService;
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
@RequestMapping("/pessoaEnderecos")
@Tag(name = "/pessoaEnderecos", description = "API para manipulação de Pessoas Endereço")
public class PessoaEnderecoResource {

	private final PessoaEnderecoService pessoaEnderecoService;
	private final PessoaEnderecoMapperImpl mapper;

	@Operation(description = "API para listar todas as Pessoas Endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<PessoaEnderecoResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<PessoaEndereco> pessoaEnderecos = pessoaEnderecoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<PessoaEnderecoResponseDTO> pessoaEnderecoDTO = pessoaEnderecos.map(mapper::toResponse);
		return ResponseEntity.ok(pessoaEnderecoDTO);
	}

	@Operation(description = "API para salvar as pessoas endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PessoaEnderecoResponseDTO> criar(@Valid @RequestBody PessoaEnderecoRequestDTO request){
		PessoaEndereco pessoa = mapper.toModel(request);
		PessoaEndereco pessoaEnderecoSalva = pessoaEnderecoService.save(pessoa);
		PessoaEnderecoResponseDTO responseDTO = mapper.toResponse(pessoaEnderecoSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para remover Pessoa Endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "pessoaId", in = ParameterIn.PATH), @Parameter(name = "enderecoId", in = ParameterIn.PATH)})
	@DeleteMapping("/pessoa/{pessoaId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer pessoaId, @PathVariable Integer enderecoId) {		
		pessoaEnderecoService.deleteByPessoaAndEndereco(pessoaId, enderecoId);
	}

	@Operation(description = "API para atualizar Pessoa Endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "pessoaId", in = ParameterIn.PATH), @Parameter(name = "enderecoId", in = ParameterIn.PATH)})
	@PutMapping("/pessoa/{pessoaId}/endereco/{enderecoId}")
	public ResponseEntity<PessoaEnderecoResponseDTO> atualizar(@PathVariable Integer pessoaId, @PathVariable Integer enderecoId,@Valid @RequestBody PessoaEnderecoRequestDTO request) {
		PessoaEndereco pessoaEndereco = mapper.toModel(request);
		PessoaEndereco pessoaEnderecoAtualizada = pessoaEnderecoService.updateByPessoaAndEndereco(pessoaId, enderecoId, pessoaEndereco);
		PessoaEnderecoResponseDTO responseDTO = mapper.toResponse(pessoaEnderecoAtualizada);
		return ResponseEntity.ok(responseDTO);
	}
	
	
}
