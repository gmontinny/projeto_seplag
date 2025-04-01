package br.gov.mt.controladoria.scsp.model.dto.endereco;

import br.gov.mt.controladoria.scsp.model.dto.cidade.CidadeRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDTO {
    private Integer id;

    @NotNull(message = "{endereco.tipoLogradouro.notnull}")
    private String tipoLogradouroEndereco;

    @NotNull(message = "{endereco.logradouro.notnull}")
    private String logradouroEndereco;

    @NotNull(message = "{endereco.numero.notnull}")
    private Integer numeroEndereco;

    @NotNull(message = "{endereco.bairro.notnull}")
    private String bairroEndereco;

    @NotNull(message = "{endereco.cidade.nulo}")
    private CidadeRequestDTO cidade;

}
