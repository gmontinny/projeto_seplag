package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ServidorEfetivoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@NotNull
	@Column(name = "se_matricula")
	private String seMatriculaServidorEfetivo;


}
