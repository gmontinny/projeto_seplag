package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.FotoPessoa;
import br.gov.mt.controladoria.scsp.repository.FotoPessoaRepository;
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
public class FotoPessoaService {

	private final FotoPessoaRepository fotoPessoaRepository;
	private final MessageSource messageSource; // Injetar MessageSource

	// Novo método para listar todas as Fotos Pessoas com paginação
	public Page<FotoPessoa> findAll(Pageable pageable) {
		return fotoPessoaRepository.findAll(pageable);
	}

	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public FotoPessoa save(FotoPessoa fotoPessoa) {
		Optional.ofNullable(fotoPessoa)
				.orElseThrow(() -> new IllegalArgumentException(
						messageSource.getMessage("fotoPessoa.nulo", null, Locale.getDefault())));

		Optional.ofNullable(fotoPessoa.getId())
				.filter(fotoPessoaRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException(
							messageSource.getMessage("fotoPessoa.alreadyexists", new Object[]{id}, Locale.getDefault()));
				});


		return fotoPessoaRepository.save(fotoPessoa);
	}

	@Transactional(rollbackFor = Exception.class)
	public FotoPessoa atualizar(Integer codigo, FotoPessoa fotoPessoa) {
		FotoPessoa fotoPessoaSalvar = buscarFotoPessoaSalvar(codigo);
		BeanUtils.copyProperties(fotoPessoa,fotoPessoaSalvar,"id");
		return fotoPessoaRepository.save(fotoPessoaSalvar);
	}

	private FotoPessoa buscarFotoPessoaSalvar(Integer codigo) {
		FotoPessoa fotoPessoaSalvar = fotoPessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return fotoPessoaSalvar;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) {
		if (!fotoPessoaRepository.existsById(id)) {
			throw new EmptyResultDataAccessException(
					messageSource.getMessage("fotoPessoa.notfound", new Object[]{id}, Locale.getDefault()), 1);
		}

		fotoPessoaRepository.deleteById(id);
	}
}
