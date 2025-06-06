package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@Embeddable
public class ServidorEfetivoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@NotNull(message = "{servidorTemporario.dataAdmissao.notnull}")
	@Column(name = "se_matricula")
	private String seMatriculaServidorEfetivo;


}
