package br.gov.mt.controladoria.scsp.model.dto.unidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeRequestDTO {
    private Integer id;

    @NotNull(message = "{unidade.nome.notnull}")
    @NotBlank(message = "{unidade.nome.notblank}")
    @Size(min = 3, max = 100, message = "{unidade.nome.size}")
    private String nomeUnidade;

    @NotNull(message = "{unidade.sigla.notnull}")
    @NotBlank(message = "{unidade.sigla.notblank}")
    @Pattern(regexp = "^[A-Za-z]{2,3}$", message = "{unidade.sigla.pattern}")
    private String siglaUnidade;
}
