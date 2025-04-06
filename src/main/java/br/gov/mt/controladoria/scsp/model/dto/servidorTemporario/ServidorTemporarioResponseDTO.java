package br.gov.mt.controladoria.scsp.model.dto.servidorTemporario;

import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaResponseDTO;

public record ServidorTemporarioResponseDTO(ServidorTemporarioId id, PessoaResponseDTO pessoa) {
}
