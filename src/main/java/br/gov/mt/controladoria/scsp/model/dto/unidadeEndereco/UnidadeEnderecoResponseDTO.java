package br.gov.mt.controladoria.scsp.model.dto.unidadeEndereco;

import br.gov.mt.controladoria.scsp.model.UnidadeEnderecoId;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoResponseDTO;
import br.gov.mt.controladoria.scsp.model.dto.unidade.UnidadeResponseDTO;

public record UnidadeEnderecoResponseDTO(UnidadeEnderecoId id, UnidadeResponseDTO unidade, EnderecoResponseDTO endereco) {
}
