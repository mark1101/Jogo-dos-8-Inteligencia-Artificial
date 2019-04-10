package JogoModelo;

/**
 * 
 * @author Mark
 * 
 */
public class BuscaEstrelaFH extends BuscaHeuristicaCalc {

        //tenho que achar meu objeto(estado) com menor heuristica para meu movimento
	public BuscaEstrelaFH(RealizaJogo estadoInicial, RealizaJogo estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

	@Override
	public int funcaoH(Estado estado) {
		RealizaJogo jogo8Final = (RealizaJogo) getEstadoFinal();
		RealizaJogo play = (RealizaJogo) estado;

		Integer[][] matrizAtual = play.getMatriz();
		Integer[][] matrizFinal = jogo8Final.getMatriz();

		int tamHeuristica = 0;
                
		// ENCONTRA POSICOES DIFERENTES SE TIVER
		for (int i = 0; i < matrizAtual.length; i++) {
			for (int j = 0; j < matrizAtual.length; j++) {
                            
                                /*faz como se fosse uma heuristica de geral assim, pra saber qual dos meus estados
                                pode ser o melhor a ser escolhido*/ 
                                
				if (!comparar(matrizAtual[i][j], matrizFinal[i][j])) { 
					tamHeuristica++;
				}
			}
		}

		return tamHeuristica;
	}
	private boolean comparar(Integer m1, Integer m2) {
		if (m1 == null) {
			if (m2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (m2 == null) {
				return false;
			} else {
				return m1.equals(m2);
			}
		}
	}

	@Override
	public String toString() {
		return "Busca Heuristica verificacao feita com valores fora do meu lugar certo  ";
	}

}