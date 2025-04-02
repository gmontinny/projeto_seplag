package br.gov.mt.controladoria.scsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.mt.controladoria.scsp.model.FotoPessoa;

import java.util.List;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Integer>{

	@EntityGraph(attributePaths = {"pessoa"})
	public Page<FotoPessoa> findAll(Pageable pageable);

	@Query("SELECT f FROM FotoPessoa f JOIN FETCH f.pessoa")
	public List<FotoPessoa> findAllWithPessoa();

}
