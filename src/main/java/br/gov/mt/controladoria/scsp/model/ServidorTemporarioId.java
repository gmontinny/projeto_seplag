package br.gov.mt.controladoria.scsp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(of = {"dataAdmissao", "dataDemissao"})
@Getter
@Setter
@Embeddable
public class ServidorTemporarioId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "{servidorTemporario.dataAdmissao.notnull}") // Mensagem customizada
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column(name = "st_data_admissao")
	private Date dataAdmissao;

	@NotNull(message = "{servidorTemporario.dataDemissao.notnull}") // Mensagem customizada
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	@Temporal(TemporalType.DATE)
	@Column(name = "st_data_demissao")
	private Date dataDemissao;

}
