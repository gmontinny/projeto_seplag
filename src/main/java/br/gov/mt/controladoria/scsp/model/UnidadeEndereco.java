package br.gov.mt.controladoria.scsp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidade_endereco")
public class UnidadeEndereco {
	
	@EmbeddedId
	private UnidadeEnderecoId id;
	
	@NotNull
	@ManyToOne
	@MapsId("unidadeId")
	@JoinColumn(name = "unid_id")
	private Unidade unidade;
	
	@NotNull
	@ManyToOne
	@MapsId("enderecoId")
	@JoinColumn(name = "end_id")
	private Endereco endereco;

}
