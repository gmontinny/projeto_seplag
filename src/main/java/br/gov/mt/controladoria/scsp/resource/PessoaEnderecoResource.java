package br.gov.mt.controladoria.scsp.resource;


import br.gov.mt.controladoria.scsp.model.PessoaEndereco;
import br.gov.mt.controladoria.scsp.repository.PessoaEnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoaEnderecos")
public class PessoaEnderecoResource {

	private final PessoaEnderecoRepository pessoaEnderecoRepository;
	
	@GetMapping
	public Page<PessoaEndereco> listar(Pageable pageable){
		return pessoaEnderecoRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<PessoaEndereco> criar(@RequestBody PessoaEndereco pessoaEndereco){
		PessoaEndereco pessoaEnderecoSalvar = pessoaEnderecoRepository.save(pessoaEndereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaEnderecoSalvar);
	}
	
	@DeleteMapping("/pessoa/{pessoaId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer pessoaId, @PathVariable Integer enderecoId) {		
		this.pessoaEnderecoRepository.deleteByPessoaAndEndereco(pessoaId, enderecoId);
	}
	
	@PutMapping("/pessoa/{pessoaId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void atualizar(@PathVariable Integer pessoaId, @PathVariable Integer enderecoId, @RequestBody PessoaEndereco pessoaEndereco) {
		this.pessoaEnderecoRepository.updateByPessoaAndEndereco(pessoaEndereco.getPessoa().getId(), pessoaEndereco.getEndereco().getId(),
				pessoaId, enderecoId);
	}
	
	
}
