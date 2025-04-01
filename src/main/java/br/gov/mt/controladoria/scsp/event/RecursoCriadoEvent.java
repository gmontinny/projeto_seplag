package br.gov.mt.controladoria.scsp.event;

import jakarta.servlet.http.HttpServletResponse; // Atualizado para Jakarta
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Evento de recurso criado utilizado em todo o sistema.
 */
@Getter
@AllArgsConstructor
@ToString
public class RecursoCriadoEvent {

	private final HttpServletResponse response; // Tornado imutável com final
	private final Integer codigo;

}