package br.gov.mt.controladoria.scsp.model.dto.lotacao;

import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.unidade.UnidadeRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LotacaoRequestDTO {
    private Integer id;

    @NotNull(message = "{lotacao.pessoa.notnull}")
    private PessoaRequestDTO pessoa;

    @NotNull(message = "{lotacao.unidade.notnull}")
    private UnidadeRequestDTO unidade;

    @NotNull(message = "{lotacao.dataLotacao.notnull}")
    private Date dataLotacao;

    @NotNull(message = "{lotacao.dataRemocao.notnull}")
    private Date dataRemocao;

    @NotNull(message = "{lotacao.portariaLotacao.notnull}")
    private String portariaLotacao;
}
