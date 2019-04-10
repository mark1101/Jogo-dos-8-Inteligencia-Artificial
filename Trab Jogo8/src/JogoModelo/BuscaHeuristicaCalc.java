package JogoModelo;


import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * 
 * @author Mark
 * 
 */
public abstract class BuscaHeuristicaCalc extends Busca {

    
       
         
	public BuscaHeuristicaCalc(EstadoHeuristicaFG estadoInicial, EstadoHeuristicaFG estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

	protected abstract int funcaoH(Estado estado);

	@Override
	protected void adicionarEstadosListaAberta(List<Estado> listaAberta, Collection<Estado> estados) {
		
                //todos meus estados ou valores da minha matriz, ficam com seu valor de H calculado
		for (Estado estado : estados) { 
			RealizaJogo jogo = (RealizaJogo) estado; //objeto do tipo jogo 
			int funcaoH = funcaoH(jogo); // valor de funcaoH recebe os valores do calc de H de cada obj
			jogo.setFuncaoH(funcaoH);  //seta o valor de H para todo obj 
		}
                
		listaAberta.addAll(estados);
		Collections.sort(listaAberta); // ordenar lista
	}

}