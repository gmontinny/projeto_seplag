package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporario {
	
	@EmbeddedId
	private ServidorTemporarioId id;

	@NotNull(message = "{pessoa.nulo}") // Mensagem customizada
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;

}
