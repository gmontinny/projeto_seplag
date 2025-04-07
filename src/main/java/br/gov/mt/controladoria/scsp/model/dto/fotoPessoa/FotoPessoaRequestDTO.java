package br.gov.mt.controladoria.scsp.model.dto.fotoPessoa;

import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FotoPessoaRequestDTO {

    private Integer id;

    @NotNull(message = "{fotoPessoa.pessoa.notnull}")
    private PessoaRequestDTO pessoa;

    @NotNull(message = "{fotoPessoa.dataFotoPessoa.notnull}")
    @PastOrPresent(message = "{fotoPessoa.dataFotoPessoa.pastOrPresent}")
    @Temporal(TemporalType.DATE)
    private Date dataFotoPessoa;

    @NotNull(message = "{fotoPessoa.bucketFotoPessoa.notnull}")
    @Size(min = 3, max = 50, message = "{fotoPessoa.bucketFotoPessoa.size}")
    private String bucketFotoPessoa;

    @NotNull(message = "{fotoPessoa.hashFotoPessoa.notnull}")
    @Pattern(regexp = "^[a-fA-F0-9]{32,64}$", message = "{fotoPessoa.hashFotoPessoa.pattern}")
    private String hashFotoPessoa;

}
