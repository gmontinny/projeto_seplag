package br.gov.mt.controladoria.scsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.controladoria.scsp.model.Lotacao;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer>{
	public Page<Lotacao> findAll(Pageable pageable);
}
