package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.Endereco;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.endereco.EnderecoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    @Mapping(target = "id", ignore = true) // Ignore the "id" field
    Endereco toModel(EnderecoRequestDTO enderecoRequestDTO);

    EnderecoResponseDTO toResponse(Endereco endereco);
}
