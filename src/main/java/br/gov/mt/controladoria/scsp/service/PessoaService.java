package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;
	private final MessageSource messageSource; // Injetar MessageSource


	// Novo método para listar todas as Pessoas com paginação
	public Page<Pessoa> findAll(Pageable pageable) {
		return pessoaRepository.findAll(pageable);
	}
	
	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public Pessoa save(Pessoa Pessoa) {
		Optional.ofNullable(Pessoa)
				.orElseThrow(() -> new IllegalArgumentException(
						messageSource.getMessage("pessoa.nulo", null, Locale.getDefault())));

		Optional.ofNullable(Pessoa.getId())
				.filter(pessoaRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException(
							messageSource.getMessage("pessoa.alreadyexists", new Object[]{id}, Locale.getDefault()));
				});


		return pessoaRepository.save(Pessoa);
	}	

	@Transactional(rollbackFor = Exception.class)
	public Pessoa atualizar(Integer codigo, Pessoa pessoa) {
		Pessoa pessoaSalvar = buscaPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalvar, "idPessoa");
		return pessoaRepository.save(pessoaSalvar);
	}

	private Pessoa buscaPessoaPeloCodigo(Integer codigo) {
		Pessoa pessoaSalvar = pessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaSalvar;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) {
		if (!pessoaRepository.existsById(id)) {
			throw new EmptyResultDataAccessException(
					messageSource.getMessage("pessoa.notfound", new Object[]{id}, Locale.getDefault()), 1);
		}

		pessoaRepository.deleteById(id);
	}	
}
