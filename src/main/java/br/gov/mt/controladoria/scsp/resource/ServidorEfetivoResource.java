package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.mapper.ServidorEfetivoMapperImpl;
import br.gov.mt.controladoria.scsp.model.ServidorEfetivo;
import br.gov.mt.controladoria.scsp.model.dto.servidorEfetivo.ServidorEfetivoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.servidorEfetivo.ServidorEfetivoResponseDTO;
import br.gov.mt.controladoria.scsp.service.ServidorEfetivoService;
import br.gov.mt.controladoria.scsp.util.Functions;
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

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servidorEfetivo")
@Tag(name = "/servidorEfetivo", description = "API para manipulação de Servidor Efetivo")
public class ServidorEfetivoResource {

	private final ServidorEfetivoService serviceEfetivoService;

	private final ServidorEfetivoMapperImpl mapper;

	@Operation(description = "API para listar todos os Servidores Efetivos")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@GetMapping
	public ResponseEntity<Page<ServidorEfetivoResponseDTO>> listar(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "coluna", defaultValue = "id") String coluna
	){
		Page<ServidorEfetivo> servidorEfetivos = serviceEfetivoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), coluna)));
		Page<ServidorEfetivoResponseDTO> servidorEfetivoDTO = servidorEfetivos.map(mapper::toResponse);
		return ResponseEntity.ok(servidorEfetivoDTO);
	}

	@Operation(description = "API para pesquisar Servidores Efetivo Por Unidade")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "idUnidade", in = ParameterIn.PATH)})
	@GetMapping(value = "/{idUnidade}/unidade", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> listaServidorEfetivo(@PathVariable Integer idUnidade, 
	        @RequestParam(defaultValue = "0") Integer page,
	        @RequestParam(defaultValue = "3") Integer size){

		return serviceEfetivoService.getServidorEfetivoAndLotacao(idUnidade, page, size).stream()
				.filter(Objects::nonNull) // Remove elementos nulos
				.map(o -> {
					Map<String, Object> dados = new HashMap<>();
					Date createdDate = (Date) o[1];
					dados.put("nome", o[0]);
					dados.put("idade", new Functions().age(createdDate, new Date()));
					dados.put("lotacao", o[2]);
					dados.put("foto", o[3]);
					return dados;
				})
				.toList(); // Retorna como lista
	}

	@Operation(description = "API para pesquisar Servidores Efetivo Por Endereço")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "nome", in = ParameterIn.PATH)})
	@GetMapping("/{nome}/endereco")
	public List<Map<String, Object>> listaServidorEfetivo(@PathVariable String nome,
	        @RequestParam(defaultValue = "0") Integer page,
	        @RequestParam(defaultValue = "3") Integer size){

			return serviceEfetivoService.getServidorEfetivoAndEnderecoEndUnidade(nome,page,size).stream()
					.filter(Objects::nonNull) // Remove elementos nulos
					.map(o -> {
						Map<String, Object> dados = new HashMap<>();
						dados.put("unidade", o[0]);
						dados.put("bairro",  o[1]);
						dados.put("tipo", o[2]);
						dados.put("logradouro", o[3]);
						return dados;
					}).toList();
	}

	@Operation(description = "API para salvar os Servidores Efetivos")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Retorno OK da Lista"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServidorEfetivoResponseDTO> criar(@Valid @RequestBody ServidorEfetivoRequestDTO request){
		ServidorEfetivo servidorEfetivo = mapper.toModel(request);
		ServidorEfetivo servidorEfetivoSalva = serviceEfetivoService.save(servidorEfetivo);
		ServidorEfetivoResponseDTO responseDTO = mapper.toResponse(servidorEfetivoSalva);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@Operation(description = "API para atualizar Servidor Efetivo")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK para atualização"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "matricula", in = ParameterIn.PATH)})
	@PutMapping(value = "/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServidorEfetivoResponseDTO> atualizar(@PathVariable String matricula,@Valid @RequestBody ServidorEfetivoRequestDTO request){
		ServidorEfetivo servidorEfetivo = mapper.toModel(request);
		ServidorEfetivo servidorEfetivoAtualizada = serviceEfetivoService.atualizar(matricula, servidorEfetivo);
		ServidorEfetivoResponseDTO responseDTO = mapper.toResponse(servidorEfetivoAtualizada);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "API para remover Servidor Efetivo")
	@ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Retorno OK da remoçao"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
			@ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
	@Parameters(value = {@Parameter(name = "matricula", in = ParameterIn.PATH)})
	@DeleteMapping("/{matricula}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String matricula) {
		this.serviceEfetivoService.deletar(matricula);
	}
	
	
}
