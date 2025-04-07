package br.gov.mt.controladoria.scsp.model.dto.unidadeEndereco;

import br.gov.mt.controladoria.scsp.model.UnidadeEnderecoId;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.unidade.UnidadeRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeEnderecoRequestDTO {

    private UnidadeEnderecoId id;

    @NotNull(message = "{unidadeEndereco.unidade.notnull}")
    private UnidadeRequestDTO unidade;

    @NotNull(message = "{unidadeEndereco.endereco.notnull}")
    private EnderecoRequestDTO endereco;
}
