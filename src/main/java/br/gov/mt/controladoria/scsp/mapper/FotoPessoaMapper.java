package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.FotoPessoa;
import br.gov.mt.controladoria.scsp.model.dto.fotoPessoa.FotoPessoaRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.fotoPessoa.FotoPessoaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FotoPessoaMapper {
    FotoPessoaMapper INSTANCE = Mappers.getMapper(FotoPessoaMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore the "id" field
    FotoPessoa toModel(FotoPessoaRequestDTO fotoPessoaRequestDTO);

    FotoPessoaResponseDTO toResponse(FotoPessoa fotoPessoa);
}
