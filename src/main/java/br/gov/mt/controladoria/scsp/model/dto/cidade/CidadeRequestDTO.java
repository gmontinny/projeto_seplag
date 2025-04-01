package br.gov.mt.controladoria.scsp.model.dto.cidade;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeRequestDTO {

    private Integer id;

    @NotNull(message = "{cidade.nome.notnull}")
    @Size(min = 2, max = 100, message = "{cidade.nome.size}")
    private String nomeCidade;

    @NotNull(message = "{cidade.uf.notnull}")
    @Pattern(regexp = "^[A-Z]{2}$", message = "{cidade.uf.pattern}")
    private String ufCidade;
}

