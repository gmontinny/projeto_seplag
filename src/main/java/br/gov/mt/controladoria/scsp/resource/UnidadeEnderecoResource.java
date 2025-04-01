package br.gov.mt.controladoria.scsp.resource;


import br.gov.mt.controladoria.scsp.model.UnidadeEndereco;
import br.gov.mt.controladoria.scsp.repository.UnidadeEnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/unidadeEnderecos")
public class UnidadeEnderecoResource {

	private final UnidadeEnderecoRepository unidadeEnderecoRepository;

	@GetMapping
	public Page<UnidadeEndereco> listar(Pageable pageable){
		return unidadeEnderecoRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<UnidadeEndereco> criar(@RequestBody UnidadeEndereco unidadeEndereco){
		UnidadeEndereco unidadeEnderecoSalvar = unidadeEnderecoRepository.save(unidadeEndereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeEnderecoSalvar);
	}
	
	@DeleteMapping("/unidade/{unidadeId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer unidadeId, @PathVariable Integer enderecoId) {		
		this.unidadeEnderecoRepository.deleteByUnidadeAndEndereco(unidadeId, enderecoId);
	}
	
	@PutMapping("/unidade/{unidadeId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void atualizar(@PathVariable Integer unidadeId, @PathVariable Integer enderecoId, @RequestBody UnidadeEndereco unidadeEndereco) {
		this.unidadeEnderecoRepository.updateByUnidadeAndEndereco(unidadeEndereco.getUnidade().getIdUnidade(), unidadeEndereco.getEndereco().getId(),
				unidadeId, enderecoId);
	}
}
