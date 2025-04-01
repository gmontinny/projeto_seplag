package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.model.Lotacao;
import br.gov.mt.controladoria.scsp.repository.LotacaoRepository;
import br.gov.mt.controladoria.scsp.service.LotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lotacoes")
public class LotacaoResource {

	private final LotacaoRepository lotacaoRepository;

	private final LotacaoService lotacaoService;
	
	@PostMapping
	public ResponseEntity<Lotacao> criar(@RequestBody Lotacao lotacao){
		Lotacao lotacaoSalvar = lotacaoRepository.save(lotacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(lotacaoSalvar);
	}
	
	@GetMapping
	public Page<Lotacao> listar(Pageable pageable){
		return lotacaoRepository.findAll(pageable);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Lotacao> atualizar(@PathVariable Integer codigo, @RequestBody Lotacao lotacao){
		Lotacao lotacaoSalvar = lotacaoService.atualizar(codigo, lotacao);
		return ResponseEntity.ok(lotacaoSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.lotacaoRepository.deleteById(codigo);
	}
}
