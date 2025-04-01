package br.gov.mt.controladoria.scsp.model.dto.endereco;

import br.gov.mt.controladoria.scsp.model.dto.cidade.CidadeResponseDTO;

public record EnderecoResponseDTO(Integer id, String tipoLogradouroEndereco, String logradouroEndereco, Integer numeroEndereco, String bairroEndereco, CidadeResponseDTO cidade) {
}
