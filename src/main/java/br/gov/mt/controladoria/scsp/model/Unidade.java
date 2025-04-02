package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	private Integer id;

	@NotNull(message = "{unidade.nome.notnull}")
	@NotBlank(message = "{unidade.nome.notblank}")
	@Size(min = 3, max = 100, message = "{unidade.nome.size}")
	@Column(name = "unid_nome")
	private String nomeUnidade;

	@NotNull(message = "{unidade.sigla.notnull}")
	@NotBlank(message = "{unidade.sigla.notblank}")
	@Pattern(regexp = "^[A-Za-z]{2,3}$", message = "{unidade.sigla.pattern}")
	@Column(name = "unid_sigla")
	private String siglaUnidade;


}
