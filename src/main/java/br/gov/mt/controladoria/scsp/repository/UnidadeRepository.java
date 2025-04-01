package br.gov.mt.controladoria.scsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.controladoria.scsp.model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
	public Page<Unidade> findAll(Pageable pageable);
}
