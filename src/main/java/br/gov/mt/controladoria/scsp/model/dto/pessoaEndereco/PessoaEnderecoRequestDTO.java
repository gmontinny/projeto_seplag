package br.gov.mt.controladoria.scsp.model.dto.pessoaEndereco;

import br.gov.mt.controladoria.scsp.model.PessoaEnderecoId;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaEnderecoRequestDTO {
    @NotNull(message = "{pessoaEndereco.id.nulo}")
    private PessoaEnderecoId id;

    @NotNull(message = "{pessoaEndereco.pessoa.notnull}")
    private PessoaRequestDTO pessoa;

    @NotNull(message = "{pessoaEndereco.endereco.notnull}")
    private EnderecoRequestDTO endereco;
}
