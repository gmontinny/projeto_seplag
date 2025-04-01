package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.Endereco;
import br.gov.mt.controladoria.scsp.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;

	// Novo método para listar todas as Enderecos com paginação
	public Page<Endereco> findAll(Pageable pageable) {
		return enderecoRepository.findAll(pageable);
	}

	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public Endereco save(Endereco Endereco) {
		Optional.ofNullable(Endereco)
				.orElseThrow(() -> new IllegalArgumentException("A entidade Endereco não pode ser nula."));

		Optional.ofNullable(Endereco.getId())
				.filter(enderecoRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException("A Endereco com o ID: " + id + " já existe.");
				});

		return enderecoRepository.save(Endereco);
	}	

	@Transactional(rollbackFor = Exception.class)
	public Endereco atualizar(Integer codigo, Endereco endereco) {
		Endereco enderecoSalvar = buscarEnderecoPeloCodigo(codigo);
		BeanUtils.copyProperties(endereco,enderecoSalvar,"id");
		return enderecoRepository.save(enderecoSalvar);
	}

	private Endereco buscarEnderecoPeloCodigo(Integer codigo) {
		Endereco enderecoSalvar = enderecoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return enderecoSalvar;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) {
		if (!enderecoRepository.existsById(id)) {
			throw new EmptyResultDataAccessException("Endereco não encontrada com o ID: " + id, 1);
		}
		enderecoRepository.deleteById(id);
	}	
}
