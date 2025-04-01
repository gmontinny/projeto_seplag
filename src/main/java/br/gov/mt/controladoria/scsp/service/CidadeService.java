package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.Cidade;
import br.gov.mt.controladoria.scsp.repository.CidadeRepository;
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
public class CidadeService {

	private final CidadeRepository cidadeRepository;
	private final MessageSource messageSource; // Injetar MessageSource


	// Novo método para listar todas as cidades com paginação
	public Page<Cidade> findAll(Pageable pageable) {
		return cidadeRepository.findAll(pageable);
	}

	// Método findById
	@Transactional(readOnly = true)
	public Optional<Cidade> findById(Integer id) {
		return cidadeRepository.findById(id);
	}

	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public Cidade save(Cidade cidade) {
		Optional.ofNullable(cidade)
				.orElseThrow(() -> new IllegalArgumentException(
						messageSource.getMessage("cidade.nulo", null, Locale.getDefault())));

		Optional.ofNullable(cidade.getId())
				.filter(cidadeRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException(
							messageSource.getMessage("cidade.alreadyexists", new Object[]{id}, Locale.getDefault()));
				});


		return cidadeRepository.save(cidade);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) {
		if (!cidadeRepository.existsById(id)) {
			throw new EmptyResultDataAccessException(
					messageSource.getMessage("cidade.notfound", new Object[]{id}, Locale.getDefault()), 1);
		}

		cidadeRepository.deleteById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Cidade atualizar(Integer codigo, Cidade cidade) {
		Cidade cidadeSalvar = bucarCidadePeloCodigo(codigo);
		BeanUtils.copyProperties(cidade, cidadeSalvar, "id");
		return cidadeRepository.save(cidadeSalvar);
	}

	private Cidade bucarCidadePeloCodigo(Integer codigo) {
		return cidadeRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(
						messageSource.getMessage("cidade.codigo.notfound", new Object[]{codigo}, Locale.getDefault()), 1));
	}
}
