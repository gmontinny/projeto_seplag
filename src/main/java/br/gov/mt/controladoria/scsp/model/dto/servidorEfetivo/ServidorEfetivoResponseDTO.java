package br.gov.mt.controladoria.scsp.model.dto.servidorEfetivo;

import br.gov.mt.controladoria.scsp.model.ServidorEfetivoId;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaResponseDTO;

public record ServidorEfetivoResponseDTO(ServidorEfetivoId id, PessoaResponseDTO pessoa) {
}
