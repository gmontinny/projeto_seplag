package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidade")
public class Unidade {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_generator")
	@SequenceGenerator(name = "unidade_generator", sequenceName = "unidade_sequence", allocationSize = 1)
	@Column(name = "unid_id")
	private Integer idUnidade;
	
	@NotNull
	@Column(name = "unid_nome")
	private String nomeUnidade;
	
	@NotNull
	@Column(name = "unid_sigla")
	private String siglaUnidade;

}
