package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.UnidadeEndereco;
import br.gov.mt.controladoria.scsp.model.UnidadeEnderecoId;
import br.gov.mt.controladoria.scsp.repository.UnidadeEnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UnidadeEnderecoService {
    private final UnidadeEnderecoRepository unidadeEnderecoRepository;
    private final MessageSource messageSource; // Injetar MessageSource

    // Novo método para listar todas as unidades endereços com paginação
    public Page<UnidadeEndereco> findAll(Pageable pageable) {
        return unidadeEnderecoRepository.findAll(pageable);
    }


    // Novo método save usando Streams
    @Transactional(rollbackFor = Exception.class)
    public UnidadeEndereco save(UnidadeEndereco unidadeEndereco) {
        Optional.ofNullable(unidadeEndereco)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("unidadeEndereco.nulo", null, Locale.getDefault())));

        Optional.ofNullable(unidadeEndereco.getId())
                .filter(unidadeEnderecoRepository::existsById)
                .ifPresent(id -> {
                    throw new IllegalArgumentException(
                            messageSource.getMessage("unidadeEndereco.alreadyexists", new Object[]{id}, Locale.getDefault()));
                });


        return unidadeEnderecoRepository.save(unidadeEndereco);
    }

    @Transactional(rollbackFor = Exception.class)
    public UnidadeEndereco atualizar(Integer unidadeId, Integer enderecoId, UnidadeEndereco unidadeEndereco) {
        unidadeEnderecoRepository.updateByUnidadeAndEndereco(unidadeEndereco.getUnidade().getId(), unidadeEndereco.getEndereco().getId(), unidadeId, enderecoId);

        UnidadeEnderecoId novaId = new UnidadeEnderecoId();
        novaId.setUnidadeId(unidadeEndereco.getUnidade().getId());
        novaId.setEnderecoId(unidadeEndereco.getEndereco().getId());

        return unidadeEnderecoRepository.findById(novaId)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("unidadeEndereco.naoEncontrado", null, Locale.getDefault())));
    }


    /**
     * Remove uma relação UnidadeEndereco pelo ID da Unidade e ID do Endereço.
     *
     * @param unidadeId  ID da Unidade
     * @param enderecoId ID do Endereço
     */
    @Transactional(rollbackFor = Exception.class)
    public void remover(Integer unidadeId, Integer enderecoId) {
        unidadeEnderecoRepository.deleteByUnidadeAndEndereco(unidadeId, enderecoId);
    }


}
