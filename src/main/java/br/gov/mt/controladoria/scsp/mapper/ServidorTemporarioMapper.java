package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.model.dto.servidorTemporario.ServidorTemporarioRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.servidorTemporario.ServidorTemporarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServidorTemporarioMapper {
    ServidorTemporarioMapper INSTANCE = Mappers.getMapper(ServidorTemporarioMapper.class);

    ServidorTemporario toModel(ServidorTemporarioRequestDTO servidorTemporarioRequestDTO);

    ServidorTemporarioResponseDTO toResponse(ServidorTemporario servidorTemporario);
}
