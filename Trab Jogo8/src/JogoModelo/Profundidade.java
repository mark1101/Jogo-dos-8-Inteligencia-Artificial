package JogoModelo;

import java.util.Collection;
import java.util.List;


/**
 * 
 * @author NeyMark
 * 
 */
public class Profundidade extends Busca {

	public Profundidade(Estado estadoInicial, Estado estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

	@Override
	protected void adicionarEstadosListaAberta(List<Estado> listaAberta, Collection<Estado> estados) {
		// Adicionar no inicio da lista
		listaAberta.addAll(0, estados);
	}

	@Override
	public String toString() {
		return "Busca em Profundidade";
	}
}