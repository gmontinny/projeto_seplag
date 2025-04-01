package br.gov.mt.controladoria.scsp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivo {

	@EmbeddedId
	private ServidorEfetivoId id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;


}
