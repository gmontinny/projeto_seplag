package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "lotacao")
public class Lotacao {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lotacao_generator")
	@SequenceGenerator(name = "lotacao_generator", sequenceName = "lotacao_sequence", allocationSize = 1)
	@Column(name = "lot_id")
	private Integer id;
	
	@NotNull(message = "{lotacao.pessoa.notnull}")
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;
	
	@NotNull(message = "{lotacao.unidade.notnull}")
	@ManyToOne
	@JoinColumn(name = "unid_id")
	private Unidade unidade;
	
	@NotNull(message = "{lotacao.dataLotacao.notnull}")
	@Temporal(TemporalType.DATE)
	@Column(name = "lot_data_lotacao")
	private Date dataLotacao;
	
	@NotNull(message = "{lotacao.dataRemocao.notnull}")
	@Temporal(TemporalType.DATE)
	@Column(name = "lot_data_remocao")
	private Date dataRemocao;
	
	@NotNull(message = "{lotacao.portariaLotacao.notnull}")
	@Column(name = "lot_portaria")
	private String portariaLotacao;

}
