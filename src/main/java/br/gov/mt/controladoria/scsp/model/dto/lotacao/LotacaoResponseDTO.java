package br.gov.mt.controladoria.scsp.model.dto.lotacao;

import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.unidade.UnidadeRequestDTO;

import java.util.Date;

public record LotacaoResponseDTO(Integer id, PessoaRequestDTO pessoa, UnidadeRequestDTO unidade,
                                 Date dataLotacao, Date dataRemocao, String portariaLotacao) {
}
