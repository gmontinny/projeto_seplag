package br.gov.mt.controladoria.scsp.repository;

import br.gov.mt.controladoria.scsp.model.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	public Page<Cidade> findByNomeCidade(String nomeCidade, Pageable pageable);
	public Page<Cidade> findAll(Pageable pageable);
}
