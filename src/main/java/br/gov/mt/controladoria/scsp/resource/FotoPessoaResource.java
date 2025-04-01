package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.model.FotoPessoa;
import br.gov.mt.controladoria.scsp.repository.FotoPessoaRepository;
import br.gov.mt.controladoria.scsp.service.FotoPessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fotoPessoas")
public class FotoPessoaResource {

	private final FotoPessoaRepository fotoPessoaRepository;

	private final FotoPessoaService fotoPessoaService;

	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<FotoPessoa> criar(@RequestBody FotoPessoa fotoPessoa){
		FotoPessoa fotoPessoaSalvar = fotoPessoaRepository.save(fotoPessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(fotoPessoaSalvar);
	}
	
	@GetMapping
	public Page<FotoPessoa> listar(Pageable pageable){
		return fotoPessoaRepository.findAll(pageable);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<FotoPessoa> atualizar(@PathVariable Integer codigo, @RequestBody FotoPessoa fotoPessoa){
		FotoPessoa fotoPessoaSalvar = fotoPessoaService.atualizar(codigo, fotoPessoa);
		return ResponseEntity.ok(fotoPessoaSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.fotoPessoaRepository.deleteById(codigo);
	}
}
