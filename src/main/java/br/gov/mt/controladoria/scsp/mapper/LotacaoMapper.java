package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.Lotacao;
import br.gov.mt.controladoria.scsp.model.dto.lotacao.LotacaoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.lotacao.LotacaoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LotacaoMapper {
    LotacaoMapper INSTANCE = Mappers.getMapper(LotacaoMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore the "id" field
    Lotacao toModel(LotacaoRequestDTO locatacaoRequestDTO);

    LotacaoResponseDTO toResponse(Lotacao lotacao);
}
