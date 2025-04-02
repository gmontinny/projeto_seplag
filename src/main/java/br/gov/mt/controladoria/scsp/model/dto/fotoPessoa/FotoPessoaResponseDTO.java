package br.gov.mt.controladoria.scsp.model.dto.fotoPessoa;

import br.gov.mt.controladoria.scsp.model.Pessoa;

import java.util.Date;

public record FotoPessoaResponseDTO(Integer id, Pessoa pessoa, Date dataFotoPessoa, String bucketFotoPessoa, String hashFotoPessoa) {
}
