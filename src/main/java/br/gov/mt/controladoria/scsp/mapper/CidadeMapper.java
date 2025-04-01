package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.Cidade;
import br.gov.mt.controladoria.scsp.model.dto.cidade.CidadeRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.cidade.CidadeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CidadeMapper {

    CidadeMapper INSTANCE = Mappers.getMapper(CidadeMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore the "id" field
    Cidade toModel(CidadeRequestDTO cidadeRequestDTO);

    CidadeResponseDTO toResponse(Cidade cidade);
}

