package br.gov.mt.controladoria.scsp.model.dto.pessoa;

import java.util.Date;

public record PessoaResponseDTO(Integer id, String nomePessoa, Date dataNascimentoPessoa, String sexoPessoa, String maePessoa, String paiPessoa) {
}
