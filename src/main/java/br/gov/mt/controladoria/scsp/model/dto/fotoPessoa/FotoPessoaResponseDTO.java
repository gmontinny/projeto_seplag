package br.gov.mt.controladoria.scsp.model.dto.fotoPessoa;

import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaResponseDTO;

import java.util.Date;

public record FotoPessoaResponseDTO(Integer id, PessoaResponseDTO pessoa, Date dataFotoPessoa, String bucketFotoPessoa, String hashFotoPessoa) {
}
