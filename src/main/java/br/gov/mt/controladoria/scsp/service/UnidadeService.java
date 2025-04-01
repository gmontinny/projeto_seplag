package br.gov.mt.controladoria.scsp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.Unidade;
import br.gov.mt.controladoria.scsp.repository.UnidadeRepository;

@Service
public class UnidadeService {
	@Autowired
	private UnidadeRepository unidadeRepository;
	
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
}
