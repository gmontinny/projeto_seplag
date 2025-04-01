package br.gov.mt.controladoria.scsp.resource;

import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;
import br.gov.mt.controladoria.scsp.repository.ServidorTemporarioRepository;
import br.gov.mt.controladoria.scsp.service.ServidorTemporarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servidorTemporario")
public class ServidorTemporarioResource {

	private final ServidorTemporarioRepository servidorTemporarioRepository;

	private final ServidorTemporarioService servidorTemporarioService;
	
	@GetMapping
	public Page<ServidorTemporario>  listar(Pageable pageable){
		return servidorTemporarioRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<ServidorTemporario> criar(@RequestBody ServidorTemporario servidorTemporario){
		ServidorTemporario servidorTemporarioSalvar = servidorTemporarioRepository.save(servidorTemporario);
		return ResponseEntity.status(HttpStatus.CREATED).body(servidorTemporarioSalvar);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ServidorTemporario> atualizar(@PathVariable Integer codigo, @RequestBody ServidorTemporario servidorTemporario){
		ServidorTemporario servidorTemporarioSalvar = servidorTemporarioService.atualizar(codigo, servidorTemporario);
		return ResponseEntity.ok(servidorTemporarioSalvar);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable ServidorTemporarioId id) {
		servidorTemporarioService.deletar(id);
	}


}
