package br.gov.mt.controladoria.scsp.model.dto.pessoaEndereco;

import br.gov.mt.controladoria.scsp.model.PessoaEnderecoId;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoResponseDTO;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaResponseDTO;

public record PessoaEnderecoResponseDTO(PessoaEnderecoId id, PessoaResponseDTO pessoa, EnderecoResponseDTO endereco) {
}
