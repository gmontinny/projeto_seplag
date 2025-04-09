package br.gov.mt.controladoria.scsp.repository;

import br.gov.mt.controladoria.scsp.model.Lotacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer>{
	@EntityGraph(attributePaths = {"pessoa", "unidade"})
	public Page<Lotacao> findAll(Pageable pageable);
}
