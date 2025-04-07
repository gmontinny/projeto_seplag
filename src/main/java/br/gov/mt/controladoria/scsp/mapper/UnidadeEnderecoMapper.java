package br.gov.mt.controladoria.scsp.mapper;

import br.gov.mt.controladoria.scsp.model.UnidadeEndereco;
import br.gov.mt.controladoria.scsp.model.dto.unidadeEndereco.UnidadeEnderecoRequestDTO;
import br.gov.mt.controladoria.scsp.model.dto.unidadeEndereco.UnidadeEnderecoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnidadeEnderecoMapper {
    UnidadeEnderecoMapper INSTANCE = Mappers.getMapper(UnidadeEnderecoMapper.class);

    UnidadeEndereco toModel(UnidadeEnderecoRequestDTO unidadeEnderecoRequestDTO);

    UnidadeEnderecoResponseDTO toResponse(UnidadeEndereco unidadeEndereco);
}
