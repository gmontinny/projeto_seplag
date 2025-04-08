package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.PessoaEndereco;
import br.gov.mt.controladoria.scsp.model.dto.pessoaEndereco.PessoaEnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.pessoaEndereco.PessoaEnderecoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaEnderecoMapper {
    PessoaEnderecoMapper INSTANCE = Mappers.getMapper(PessoaEnderecoMapper.class);

    PessoaEndereco toModel(PessoaEnderecoRequestDTO pessoaEnderecoRequestDTO);

    PessoaEnderecoResponseDTO toResponse(PessoaEndereco pessoaEndereco);
}
