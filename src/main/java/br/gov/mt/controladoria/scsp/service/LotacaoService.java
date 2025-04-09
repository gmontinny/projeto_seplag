package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.Lotacao;
import br.gov.mt.controladoria.scsp.repository.LotacaoRepository;
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
public class LotacaoService {

	private final LotacaoRepository lotacaoRepository;
	private final MessageSource messageSource; // Injetar MessageSource


	// Novo método para listar todas as lotações com paginação
	public Page<Lotacao> findAll(Pageable pageable) {
		return lotacaoRepository.findAll(pageable);
	}

	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public Lotacao save(Lotacao lotacao) {
		Optional.ofNullable(lotacao)
				.orElseThrow(() -> new IllegalArgumentException(
						messageSource.getMessage("lotacao.nulo", null, Locale.getDefault())));

		Optional.ofNullable(lotacao.getId())
				.filter(lotacaoRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException(
							messageSource.getMessage("lotacao.alreadyexists", new Object[]{id}, Locale.getDefault()));
				});


		return lotacaoRepository.save(lotacao);
	}

	@Transactional(rollbackFor = Exception.class)
	public Lotacao atualizar(Integer codigo, Lotacao lotacao) {
		Lotacao lotacaoSalvar = buscaLotacaoPeloCodigo(codigo);
		BeanUtils.copyProperties(lotacao,lotacaoSalvar,"id");
		return lotacaoRepository.save(lotacaoSalvar);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) {
		if (!lotacaoRepository.existsById(id)) {
			throw new EmptyResultDataAccessException(
					messageSource.getMessage("lotacao.notfound", new Object[]{id}, Locale.getDefault()), 1);
		}

		lotacaoRepository.deleteById(id);
	}

	private Lotacao buscaLotacaoPeloCodigo(Integer codigo) {
		Lotacao lotacaoSalvar = lotacaoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return lotacaoSalvar;
	}
}
