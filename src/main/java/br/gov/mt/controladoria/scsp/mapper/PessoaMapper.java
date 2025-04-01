package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.pessoa.PessoaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore the "id" field
    Pessoa toModel(PessoaRequestDTO pessoaRequestDTO);

    PessoaResponseDTO toResponse(Pessoa pessoa);
}
