package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.Unidade;

import br.gov.mt.controladoria.scsp.model.dto.unidade.UnidadeRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.unidade.UnidadeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {
    UnidadeMapper INSTANCE = Mappers.getMapper(UnidadeMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore the "id" field
    Unidade toModel(UnidadeRequestDTO UnidadeRequestDTO);

    UnidadeResponseDTO toResponse(Unidade Unidade);
}
