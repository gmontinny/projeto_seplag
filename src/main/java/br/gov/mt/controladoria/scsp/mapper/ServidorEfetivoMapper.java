package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.ServidorEfetivo;
import br.gov.mt.controladoria.scsp.model.dto.servidorEfetivo.ServidorEfetivoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.servidorEfetivo.ServidorEfetivoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServidorEfetivoMapper {
    ServidorEfetivoMapper INSTANCE = Mappers.getMapper(ServidorEfetivoMapper.class);

    ServidorEfetivo toModel(ServidorEfetivoRequestDTO servidorEfetivoRequestDTO);

    ServidorEfetivoResponseDTO toResponse(ServidorEfetivo servidorEfetivo);
}
