package br.gov.mt.controladoria.scsp.model.dto.servidorTemporario;

import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServidorTemporarioRequestDTO {
    private ServidorTemporarioId id;

    @NotNull(message = "{pessoa.nulo}") // Mensagem customizada
    private Pessoa pessoa;
}
