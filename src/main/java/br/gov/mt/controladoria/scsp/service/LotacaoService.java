package br.gov.mt.controladoria.scsp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.Lotacao;
import br.gov.mt.controladoria.scsp.repository.LotacaoRepository;

@Service
public class LotacaoService {
	@Autowired
	LotacaoRepository lotacaoRepository;
	
	public Lotacao atualizar(Integer codigo, Lotacao lotacao) {
		Lotacao lotacaoSalvar = buscaLotacaoPeloCodigo(codigo);
		BeanUtils.copyProperties(lotacao,lotacaoSalvar,"idLotacao");
		return lotacaoRepository.save(lotacaoSalvar);
	}

	private Lotacao buscaLotacaoPeloCodigo(Integer codigo) {
		Lotacao lotacaoSalvar = lotacaoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return lotacaoSalvar;
	}
}
