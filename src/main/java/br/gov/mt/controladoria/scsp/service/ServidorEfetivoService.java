package br.gov.mt.controladoria.scsp.service;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import br.gov.mt.controladoria.scsp.model.ServidorEfetivo;
import br.gov.mt.controladoria.scsp.repository.ServidorEfetivoRepository;

@Service
public class ServidorEfetivoService {
	@Autowired
	ServidorEfetivoRepository servidorEfetivoRepository;
	
	@Autowired
	PessoaService pessoaService;
		
	public ServidorEfetivo atualizar(String matricula, ServidorEfetivo servidorEfetivo) {
		ServidorEfetivo servidorEfetivoSalvar = buscarServidorEfetivoPorMatricula(matricula);
		servidorEfetivo.setPessoa(pessoaService.atualizar(servidorEfetivo.getPessoa().getId(), servidorEfetivo.getPessoa()));
		BeanUtils.copyProperties(servidorEfetivo,servidorEfetivoSalvar,"id.seMatriculaServidorEfetivo");
		return servidorEfetivoRepository.save(servidorEfetivoSalvar);
	}
	
	public void deletar(String matricula) {
		ServidorEfetivo servidorEfetivo = buscarServidorEfetivoPorMatricula(matricula);
		servidorEfetivoRepository.delete(servidorEfetivo);
	}

	private ServidorEfetivo buscarServidorEfetivoPorMatricula(String matricula) {
		ServidorEfetivo servidorEfetivoSalvar = servidorEfetivoRepository.findByIdSeMatriculaServidorEfetivo(matricula)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
				
		return servidorEfetivoSalvar;
	}
	
	public List<Object[]> getServidorEfetivoAndLotacao(Integer codigo, Integer page, Integer size ){
		Pageable paging = PageRequest.of(page, size);
		
		Page<Object[]> pagedResult = servidorEfetivoRepository.findByServidorEfetivoAndLotacao(codigo, paging);
		
		return pagedResult.getContent();
	}
	
	public List<Object[]> getServidorEfetivoAndEnderecoEndUnidade(String nome, Integer page, Integer size ){
		Pageable paging = PageRequest.of(page, size);
		
		Page<Object[]> pagedResult = servidorEfetivoRepository.findByServidorEfetivoAndEnderecoEndUnidadeIgnoreCase(nome, paging);
		
		return pagedResult.getContent();
	}
}
