package br.gov.mt.controladoria.scsp.model.dto.pessoa;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PessoaRequestDTO {
    private Integer id;

    @NotNull(message = "{pessoa.nomePessoa.notnull}")
    @Size(min = 2, max = 100, message = "{pessoa.nomePessoa.size}")
    private String nomePessoa;

    @NotNull(message = "{pessoa.dataNascimentoPessoa.notnull}")
    @Temporal(TemporalType.DATE)
    private Date dataNascimentoPessoa;

    @NotNull(message = "{pessoa.sexoPessoa.notnull}")
    @Pattern(regexp = "M|F", message = "{pessoa.sexoPessoa.pattern}")
    private String sexoPessoa;

    @NotNull(message = "{pessoa.maePessoa.notnull}")
    private String maePessoa;

    @NotNull(message = "{pessoa.paiPessoa.notnull}")
    private String paiPessoa;
}
