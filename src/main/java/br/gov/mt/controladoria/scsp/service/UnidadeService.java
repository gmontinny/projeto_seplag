package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.Unidade;
import br.gov.mt.controladoria.scsp.repository.UnidadeRepository;
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
public class UnidadeService {

	private final UnidadeRepository unidadeRepository;
	private final MessageSource messageSource; // Injetar MessageSource

	// Novo método para listar todas as Unidades com paginação
	public Page<Unidade> findAll(Pageable pageable) {
		return unidadeRepository.findAll(pageable);
	}

	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public Unidade save(Unidade unidade) {
		Optional.ofNullable(unidade)
				.orElseThrow(() -> new IllegalArgumentException(
						messageSource.getMessage("unidade.nulo", null, Locale.getDefault())));

		Optional.ofNullable(unidade.getId())
				.filter(unidadeRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException(
							messageSource.getMessage("unidade.alreadyexists", new Object[]{id}, Locale.getDefault()));
				});


		return unidadeRepository.save(unidade);
	}

	@Transactional(rollbackFor = Exception.class)
	public Unidade atualizar(Integer codigo, Unidade unidade) {
		Unidade unidadeSalvar = buscaUnidadePeloCodigo(codigo);
		BeanUtils.copyProperties(unidade,unidadeSalvar,"idUnidade");
		return unidadeSalvar;
	}

	private Unidade buscaUnidadePeloCodigo(Integer codigo) {
		Unidade unidadeSalvar = unidadeRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return unidadeSalvar;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) {
		if (!unidadeRepository.existsById(id)) {
			throw new EmptyResultDataAccessException(
					messageSource.getMessage("unidade.notfound", new Object[]{id}, Locale.getDefault()), 1);
		}
		unidadeRepository.deleteById(id);
	}
}
