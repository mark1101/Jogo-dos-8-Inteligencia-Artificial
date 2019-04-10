/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JogoModelo;

/**
 *
 * @author mark_
 */
public class HeuristicaManhattan extends BuscaHeuristicaCalc {
    
         public HeuristicaManhattan(RealizaJogo estadoInicial, RealizaJogo estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

    @Override
	public int funcaoH(Estado estado) {
		RealizaJogo jogoFinal = (RealizaJogo) getEstadoFinal();
		RealizaJogo JogoAt = (RealizaJogo) estado;

		Integer[][] matrizAtual = JogoAt.getMatriz(); // pego minha matriz do meu estado inicial 
		Integer[][] matrizFinal = jogoFinal.getMatriz(); // chamo a matriz do meu estado final 

		int resultado = 0;

		//faço pesquisas de estado com a matriz que foi mandada aqui 
		for (int i = 0; i < matrizAtual.length; i++) {
			for (int j = 0; j < matrizAtual[i].length; j++) {
				int[] lugar = procurar(matrizFinal, matrizAtual[i][j]); // pesquisa meu valor na matriz 
                                resultado += calcularDistancia(lugar[0], i, lugar[1], j); // calc da distancia do valor com o estado final 
			}
		}

		return resultado;
	}
        
        // fazemos uma busca atravez do numero dentro da minha matriz 
	private int[] procurar(Integer[][] matriz, Integer idPesquisa) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == null) {
					if (idPesquisa == null) {
						return new int[] { i, j };
					}
				} else {
					if (matriz[i][j] == idPesquisa) {
						return new int[] { i, j };
					}
				}
			}
		}
		throw new IllegalStateException("Erro de chegada");
	}

        //faz o meu calculo da minha posicao atual ate a minha final  
	private int calcularDistancia(int linicial, int lfinal, int cinicial, int cfinal) {
		return Math.abs((linicial + cinicial) - (lfinal + cfinal));
	}

	@Override
	public String toString() {
		return "Busca Heuristica usando a distancia de manhattan ";
	}


}
