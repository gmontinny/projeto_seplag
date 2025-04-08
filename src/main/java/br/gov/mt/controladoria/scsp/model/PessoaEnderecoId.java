package br.gov.mt.controladoria.scsp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(of = {"pessoaId", "enderecoId"})
@Getter
@Setter
@Embeddable
public class PessoaEnderecoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @NotNull(message = "{pessoaEndereco.pessoa.notnull}")
    @Column(name = "pes_id")
    private Integer pessoaId;

    @NotNull(message = "{pessoaEndereco.endereco.notnull}")
    @Column(name = "end_id")
    private Integer enderecoId;


}
