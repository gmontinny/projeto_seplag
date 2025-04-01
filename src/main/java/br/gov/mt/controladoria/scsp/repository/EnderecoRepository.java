package br.gov.mt.controladoria.scsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.controladoria.scsp.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	public Page<Endereco> findAll(Pageable pageable);
}
