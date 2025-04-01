package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Embeddable
public class ServidorTemporarioId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "st_data_admissao")
	private Date dataAdmissao;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "st_data_demissao")
	private Date dataDemissao;

}
