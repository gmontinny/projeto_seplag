package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_generator")
	@SequenceGenerator(name = "endereco_generator", sequenceName = "endereco_sequence", allocationSize = 1)
	@Column(name = "end_id")
	private Integer id;

	@NotNull(message = "{endereco.tipoLogradouro.notnull}")
	@Column(name = "end_tipo_logradouro")
	private String tipoLogradouroEndereco;

	@NotNull(message = "{endereco.logradouro.notnull}")
	@Column(name = "end_logradouro")
	private String logradouroEndereco;
	
	@Column(name = "end_numero")
	private Integer numeroEndereco;

	@NotNull(message = "{endereco.bairro.notnull}")
	@Column(name = "end_bairro")
	private String bairroEndereco;

	@NotNull(message = "{endereco.cidade.nulo}")
	@ManyToOne
	@JoinColumn(name = "cid_id")
	private Cidade cidade;

}
