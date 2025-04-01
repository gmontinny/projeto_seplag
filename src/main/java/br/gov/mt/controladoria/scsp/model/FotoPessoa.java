package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
	private Integer idFotoPessoa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fp_data")
	private Date dataFotoPessoa;
	
	@NotNull
	@Column(name = "fp_bucket")
	private String bucketFotoPessoa;
	
	@NotNull
	@Column(name = "fp_hash")
	private String hashFotoPessoa;

}
