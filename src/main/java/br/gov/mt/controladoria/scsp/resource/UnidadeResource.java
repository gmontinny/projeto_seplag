package br.gov.mt.controladoria.scsp.resource;


import br.gov.mt.controladoria.scsp.model.Unidade;
import br.gov.mt.controladoria.scsp.repository.UnidadeRepository;
import br.gov.mt.controladoria.scsp.service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/unidades")
public class UnidadeResource {

	private final UnidadeRepository unidadeRepository;

	private final UnidadeService unidadeService;
	
	@GetMapping
	public Page<Unidade>  listar(Pageable pageable){
		return unidadeRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<Unidade> criar(@RequestBody Unidade unidade){
		Unidade unidadeSalvar = unidadeRepository.save(unidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeSalvar);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Unidade> atualizar(@PathVariable Integer codigo, @RequestBody Unidade unidade){
		Unidade unidadeSalvar = unidadeService.atualizar(codigo, unidade);
		return ResponseEntity.ok(unidadeSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.unidadeRepository.deleteById(codigo);
	}
	
}
