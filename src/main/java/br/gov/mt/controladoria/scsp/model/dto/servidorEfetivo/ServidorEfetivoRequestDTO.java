package br.gov.mt.controladoria.scsp.model.dto.servidorEfetivo;

import br.gov.mt.controladoria.scsp.model.ServidorEfetivoId;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServidorEfetivoRequestDTO {

    private ServidorEfetivoId id;

    @NotNull(message = "{servidorTemporario.pessoa.notnull}")
    private PessoaRequestDTO pessoa;
}
