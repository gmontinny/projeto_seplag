package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pessoa_endereco")
public class PessoaEndereco {
	
	@EmbeddedId
	@NotNull(message = "{pessoaEndereco.id.nulo}")
	PessoaEnderecoId id;

	@NotNull(message = "{pessoaEndereco.pessoa.notnull}")
	@ManyToOne
	@MapsId("pessoaId")
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;

	@NotNull(message = "{pessoaEndereco.endereco.notnull}")
	@ManyToOne
	@MapsId("enderecoId")
	@JoinColumn(name = "end_id")
	private Endereco endereco;

	
}
