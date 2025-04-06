package br.gov.mt.controladoria.scsp.service;


import br.gov.mt.controladoria.scsp.model.Cidade;
import br.gov.mt.controladoria.scsp.model.ServidorEfetivo;
import br.gov.mt.controladoria.scsp.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServidorEfetivoService {

	private final ServidorEfetivoRepository servidorEfetivoRepository;
	private final MessageSource messageSource; // Injetar MessageSource
	private final PessoaService pessoaService;

	// Novo método para listar todas as Servidor Efetivo com paginação
	public Page<ServidorEfetivo> findAll(Pageable pageable) {
		return servidorEfetivoRepository.findAll(pageable);
	}

	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public ServidorEfetivo save(ServidorEfetivo servidorEfetivo) {
		Optional.ofNullable(servidorEfetivo)
				.orElseThrow(() -> new IllegalArgumentException(
						messageSource.getMessage("servidorEfetivo.nulo", null, Locale.getDefault())));

		Optional.ofNullable(servidorEfetivo.getId())
				.filter(servidorEfetivoRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException(
							messageSource.getMessage("servidorEfetivo.alreadyexists", new Object[]{id}, Locale.getDefault()));
				});


		return servidorEfetivoRepository.save(servidorEfetivo);
	}

	@Transactional(rollbackFor = Exception.class)
	public ServidorEfetivo atualizar(String matricula, ServidorEfetivo servidorEfetivo) {
		ServidorEfetivo servidorEfetivoSalvar = buscarServidorEfetivoPorMatricula(matricula);
		servidorEfetivo.setPessoa(pessoaService.atualizar(servidorEfetivo.getPessoa().getId(), servidorEfetivo.getPessoa()));
		BeanUtils.copyProperties(servidorEfetivo,servidorEfetivoSalvar,"id.seMatriculaServidorEfetivo");
		return servidorEfetivoRepository.save(servidorEfetivoSalvar);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletar(String matricula) {
		ServidorEfetivo servidorEfetivo = buscarServidorEfetivoPorMatricula(matricula);
		servidorEfetivoRepository.delete(servidorEfetivo);
	}

	private ServidorEfetivo buscarServidorEfetivoPorMatricula(String matricula) {
		ServidorEfetivo servidorEfetivoSalvar = servidorEfetivoRepository.findByIdSeMatriculaServidorEfetivo(matricula)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
				
		return servidorEfetivoSalvar;
	}
	
	public List<Object[]> getServidorEfetivoAndLotacao(Integer codigo, Integer page, Integer size ){
		Pageable paging = PageRequest.of(page, size);
		
		Page<Object[]> pagedResult = servidorEfetivoRepository.findByServidorEfetivoAndLotacao(codigo, paging);
		
		return pagedResult.getContent();
	}
	
	public List<Object[]> getServidorEfetivoAndEnderecoEndUnidade(String nome, Integer page, Integer size ){
		Pageable paging = PageRequest.of(page, size);
		
		Page<Object[]> pagedResult = servidorEfetivoRepository.findByServidorEfetivoAndEnderecoEndUnidadeIgnoreCase(nome, paging);
		
		return pagedResult.getContent();
	}
}
