package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(of = {"unidadeId", "enderecoId"})
@Getter
@Setter
@Embeddable
public class UnidadeEnderecoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Column(name = "unid_id")
    private Integer unidadeId;

    @Column(name = "end_id")
    private Integer enderecoId;

}
