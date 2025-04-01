package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "cidade")
public class Cidade {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_generator")
	@SequenceGenerator(name = "cidade_generator", sequenceName = "cidade_sequence", allocationSize = 1)
	@Column(name = "cid_id")
	private Integer id;
	
	@NotNull(message = "{cidade.nome.notnull}")
	@Size(min = 2, max = 100, message = "{cidade.nome.size}")
	@Column(name = "cid_nome")
	private String nomeCidade;
	
	@NotNull(message = "{cidade.uf.notnull}")
	@Pattern(regexp = "^[A-Z]{2}$", message = "{cidade.uf.pattern}")
	@Column(name = "cid_uf")
	private String ufCidade;

}
