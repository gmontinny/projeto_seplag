package br.gov.mt.controladoria.scsp.resource;


import br.gov.mt.controladoria.scsp.mapper.EnderecoMapper;
import br.gov.mt.controladoria.scsp.mapper.EnderecoMapperImpl;
import br.gov.mt.controladoria.scsp.model.Endereco;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoResponseDTO;
import br.gov.mt.controladoria.scsp.service.EnderecoService;
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
@RequestMapping("/enderecos")
@Tag(name = "/enderecos", description = "API para manipulação de Endereços")
public class EnderecoResource {

	private final EnderecoService enderecoService;
	private final EnderecoMapperImpl mapper;

	@Operation(description = "API para salvar os Enderecos")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnderecoResponseDTO> criar(@Valid @RequestBody EnderecoRequestDTO request){
		Endereco Endereco = mapper.toModel(request);
		Endereco EnderecoSalva = enderecoService.save(Endereco);
		EnderecoResponseDTO responseDTO = mapper.toResponse(EnderecoSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para todas listar os Endereços")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<EnderecoResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna){

		Page<Endereco> enderecos = enderecoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<EnderecoResponseDTO> enderecosDTO = enderecos.map(mapper::toResponse);
		return ResponseEntity.ok(enderecosDTO);
	}

	@Operation(description = "API para atualizar Endereços")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@PutMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Integer codigo,@Valid @RequestBody EnderecoRequestDTO request){
		Endereco endereco = mapper.toModel(request);
		Endereco enderecoAtualizada = enderecoService.atualizar(codigo, endereco);
		EnderecoResponseDTO responseDTO = mapper.toResponse(enderecoAtualizada);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para remover Endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "codigo", in = ParameterIn.PATH)})
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		enderecoService.deleteById(codigo);
	}
}
