package br.gov.mt.controladoria.scsp.service;

import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.repository.ServidorTemporarioRepository;

import java.util.Date;

@Service
public class ServidorTemporarioService {
	@Autowired
	ServidorTemporarioRepository servidorTemporarioRepository;
	
	public ServidorTemporario atualizar(Integer codigo, ServidorTemporario servidorTemporario) {		
		return servidorTemporarioRepository.save(servidorTemporario);
	}

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
