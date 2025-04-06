package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;
import br.gov.mt.controladoria.scsp.repository.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServidorTemporarioService {

	public final ServidorTemporarioRepository servidorTemporarioRepository;
	private final MessageSource messageSource; // Injetar MessageSource

	// Novo método para listar todas os Servidores Temporarios com paginação
	public Page<ServidorTemporario> findAll(Pageable pageable) {
		return servidorTemporarioRepository.findAll(pageable);
	}

	// Novo método save usando Streams
	@Transactional(rollbackFor = Exception.class)
	public ServidorTemporario save(ServidorTemporario servidorTemporario) {
		Optional.ofNullable(servidorTemporario)
				.orElseThrow(() -> new IllegalArgumentException(
						messageSource.getMessage("servidorTemporario.nulo", null, Locale.getDefault())));

		Optional.ofNullable(servidorTemporario.getId())
				.filter(servidorTemporarioRepository::existsById)
				.ifPresent(id -> {
					throw new IllegalArgumentException(
							messageSource.getMessage("servidorTemporario.alreadyexists", new Object[]{id}, Locale.getDefault()));
				});


		return servidorTemporarioRepository.save(servidorTemporario);
	}

	@Transactional(rollbackFor = Exception.class)
	public ServidorTemporario atualizar(Integer codigo, ServidorTemporario servidorTemporario) {		
		return servidorTemporarioRepository.save(servidorTemporario);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletar(ServidorTemporarioId id) {
		ServidorTemporario servidorTemporario = servidorTemporarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Servidor Temporário não encontrado."));

		servidorTemporarioRepository.delete(servidorTemporario);
	}


	private ServidorTemporario buscaServidorTemporario(Date admissao, Date demissao) {

		// Busca baseada nas datas específicas
		ServidorTemporario servidorTemporarioSalvar = servidorTemporarioRepository
				.findTop1ByIdDataAdmissaoAndIdDataDemissaoOrderByPessoaIdAsc(admissao, demissao)
				.get(0);
		return servidorTemporarioSalvar;


	}
}
