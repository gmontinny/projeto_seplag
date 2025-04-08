package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.PessoaEndereco;
import br.gov.mt.controladoria.scsp.repository.PessoaEnderecoRepository;
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
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository pessoaEnderecoRepository;
    private final MessageSource messageSource; // Injetar MessageSource


    // Novo método para listar todas as pessoas endereco com paginação
    public Page<PessoaEndereco> findAll(Pageable pageable) {
        return pessoaEnderecoRepository.findAll(pageable);
    }

    // Novo método save usando Streams
    @Transactional(rollbackFor = Exception.class)
    public PessoaEndereco save(PessoaEndereco pessoaEndereco) {
        Optional.ofNullable(pessoaEndereco)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("pessoaEndereco.nulo", null, Locale.getDefault())));

        Optional.ofNullable(pessoaEndereco.getId())
                .filter(pessoaEnderecoRepository::existsById)
                .ifPresent(id -> {
                    throw new IllegalArgumentException(
                            messageSource.getMessage("pessoaEndereco.alreadyexists", new Object[]{id}, Locale.getDefault()));
                });


        return pessoaEnderecoRepository.save(pessoaEndereco);
    }
    

    /**
     * Deleta uma associação PessoaEndereco baseada nos IDs da pessoa e do endereço.
     *
     * @param idPessoa  ID da pessoa.
     * @param idEndereco ID do endereço.
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPessoaAndEndereco(Integer idPessoa, Integer idEndereco) {
        pessoaEnderecoRepository.deleteByPessoaAndEndereco(idPessoa, idEndereco);
    }

    /**
     * Atualiza uma associação PessoaEndereco com novos valores para pessoa e endereço.
     *
     * @param idNPessoa  Novo ID da pessoa.
     * @param idNEndereco Novo ID do endereço.
     */
    @Transactional(rollbackFor = Exception.class)
    public PessoaEndereco updateByPessoaAndEndereco(Integer idNPessoa, Integer idNEndereco, PessoaEndereco pessoaEndereco) {
        pessoaEnderecoRepository.updateByPessoaAndEndereco(idNPessoa, idNEndereco, pessoaEndereco.getPessoa().getId(), pessoaEndereco.getEndereco().getId());

        // Busca o PessoaEndereco atualizado para retornar
        return pessoaEnderecoRepository.findByPessoaAndEndereco(idNPessoa, idNEndereco)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage("pessoaEndereco.naoencontrado", null, Locale.getDefault())));
    }

}
