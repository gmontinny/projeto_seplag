package br.gov.mt.controladoria.scsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.controladoria.scsp.model.PessoaEndereco;
import br.gov.mt.controladoria.scsp.model.PessoaEnderecoId;

import java.util.Optional;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoId>{

	@EntityGraph(attributePaths = {"pessoa", "endereco"})
	public Page<PessoaEndereco> findAll(Pageable pageable);

	@Query("SELECT pe FROM PessoaEndereco pe WHERE pe.pessoa.id = :idPessoa AND pe.endereco.id = :idEndereco")
	public Optional<PessoaEndereco> findByPessoaAndEndereco(@Param("idPessoa") Integer idPessoa, @Param("idEndereco") Integer idEndereco);


	@Transactional
	@Modifying
	@Query("DELETE FROM PessoaEndereco pe WHERE pe.pessoa.id = :idPessoa AND pe.endereco.id = :idEndereco")
	public void deleteByPessoaAndEndereco(@Param("idPessoa") Integer idPessoa, @Param("idEndereco") Integer idEndereco);	
	
	@Transactional
	@Modifying
	@Query("UPDATE PessoaEndereco pe SET pe.pessoa.id = :idNPessoa, pe.endereco.id = :idNEndereco "
			+ " WHERE pe.pessoa.id = :idPessoa AND pe.endereco.id = :idEndereco ")
	public void updateByPessoaAndEndereco(@Param("idNPessoa") Integer idNPessoa, @Param("idNEndereco") Integer idNEndereco,
			@Param("idPessoa") Integer idPessoa, @Param("idEndereco") Integer idEndereco);
}
