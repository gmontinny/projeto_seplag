package br.gov.mt.controladoria.scsp.model.dto.servidorTemporario;

import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServidorTemporarioRequestDTO {
    private ServidorTemporarioId id;

    @NotNull(message = "{pessoa.nulo}") // Mensagem customizada
    private PessoaRequestDTO pessoa;
}
