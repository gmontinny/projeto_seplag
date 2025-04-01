package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_generator")
	@SequenceGenerator(name = "pessoa_generator", sequenceName = "pessoa_sequence", allocationSize = 1)
	@Column(name = "pes_id")
	private Integer id;

	@NotNull(message = "{pessoa.nomePessoa.notnull}")
	@Size(min = 2, max = 100, message = "{pessoa.nomePessoa.size}")
	@Column(name = "pes_nome")
	private String nomePessoa;

	@NotNull(message = "{pessoa.dataNascimentoPessoa.notnull}")
	@Temporal(TemporalType.DATE)
	@Column(name = "pes_data_nascimento")
	private Date dataNascimentoPessoa;

	@NotNull(message = "{pessoa.sexoPessoa.notnull}")
	@Pattern(regexp = "M|F", message = "{pessoa.sexoPessoa.pattern}")
	@Column(name = "pes_sexo")
	private String sexoPessoa;

	@NotNull(message = "{pessoa.maePessoa.notnull}")
	@Column(name = "pes_mae")
	private String maePessoa;

	@NotNull(message = "{pessoa.paiPessoa.notnull}")
	@Column(name = "pes_pai")
	private String paiPessoa;


}
