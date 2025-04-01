package br.gov.mt.controladoria.scsp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.FotoPessoa;
import br.gov.mt.controladoria.scsp.repository.FotoPessoaRepository;

@Service
public class FotoPessoaService {
	@Autowired
	FotoPessoaRepository fotoPessoaRepository;
	
	public FotoPessoa atualizar(Integer codigo, FotoPessoa fotoPessoa) {
		FotoPessoa fotoPessoaSalvar = buscarFotoPessoaSalvar(codigo);
		BeanUtils.copyProperties(fotoPessoa,fotoPessoaSalvar,"idFotoPessoa");
		return fotoPessoaRepository.save(fotoPessoaSalvar);
	}

	private FotoPessoa buscarFotoPessoaSalvar(Integer codigo) {
		FotoPessoa fotoPessoaSalvar = fotoPessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return fotoPessoaSalvar;
	}
}
