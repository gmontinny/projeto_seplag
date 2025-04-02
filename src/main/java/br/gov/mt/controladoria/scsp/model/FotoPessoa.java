package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "foto_pessoa")
public class FotoPessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foto_pessoa_generator")
	@SequenceGenerator(name = "foto_pessoa_generator", sequenceName = "foto_pessoa_sequence", allocationSize = 1)
	@Column(name = "fp_id")
	private Integer id;

	@NotNull(message = "{fotoPessoa.pessoa.notnull}")
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;

	@NotNull(message = "{fotoPessoa.dataFotoPessoa.notnull}")
	@PastOrPresent(message = "{fotoPessoa.dataFotoPessoa.pastOrPresent}")
	@Temporal(TemporalType.DATE)
	@Column(name = "fp_data")
	private Date dataFotoPessoa;

	@NotNull(message = "{fotoPessoa.bucketFotoPessoa.notnull}")
	@Size(min = 3, max = 50, message = "{fotoPessoa.bucketFotoPessoa.size}")
	@Column(name = "fp_bucket")
	private String bucketFotoPessoa;

	@NotNull(message = "{fotoPessoa.hashFotoPessoa.notnull}")
	@Pattern(regexp = "^[a-fA-F0-9]{32,64}$", message = "{fotoPessoa.hashFotoPessoa.pattern}")
	@Column(name = "fp_hash")
	private String hashFotoPessoa;


}
