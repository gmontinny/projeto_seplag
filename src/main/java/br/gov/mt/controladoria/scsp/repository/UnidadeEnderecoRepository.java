package br.gov.mt.controladoria.scsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.controladoria.scsp.model.UnidadeEndereco;
import br.gov.mt.controladoria.scsp.model.UnidadeEnderecoId;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, UnidadeEnderecoId>{
	public Page<UnidadeEndereco> findAll(Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM UnidadeEndereco ue WHERE ue.unidade.idUnidade = :idUnidade AND ue.endereco.id = :idEndereco")
	public void deleteByUnidadeAndEndereco(@Param("idUnidade") Integer idUnidade, @Param("idEndereco") Integer idEndereco);	
	
	@Transactional
	@Modifying
	@Query("UPDATE UnidadeEndereco ue SET ue.unidade.idUnidade = :idNUnidade, ue.endereco.id = :idNEndereco "
			+ " WHERE ue.unidade.idUnidade = :idUnidade AND ue.endereco.id = :idEndereco ")
	public void updateByUnidadeAndEndereco(@Param("idNUnidade") Integer idNUnidade, @Param("idNEndereco") Integer idNEndereco,
			@Param("idUnidade") Integer idUnidade, @Param("idEndereco") Integer idEndereco);
}
