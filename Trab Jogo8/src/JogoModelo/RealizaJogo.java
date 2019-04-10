package JogoModelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * Classe que representa um estado do Jogo do 8.
 * 
 * @author Mark
 * 
 */
public class RealizaJogo implements EstadoHeuristicaFG {

	
	private Integer[][] matriz; //MATRIZ ESTADO ATUAL

	
	private RealizaJogo pai; // ARMAZENA MEU ESTADO PAI 

	
	private int funcaoG; //FUNCAO G DO MEU ESTADO 

	
	private int funcaoH; // FUNCAO H DESTE ESTADO 

	
        // POSICOES AONDE ENCONTRO MINHA MATRIZ VAZIA
	private int lugarColunaVazia;
	private int lugarLinhaVazia;

	public RealizaJogo(RealizaJogo pai) {
		this(pai, MexeMatrizes.gerarMatrizRandomica()); //INICIA COM MINHA MATRIZ RANDOMICA
	}

	public RealizaJogo(RealizaJogo pai, Integer[][] matriz) {
		this.pai = pai;

                //QUANDO TIVER UM PAI, FACO A FUNCAO G EM CIMA DELE 
		if (pai != null) {
			funcaoG = pai.getFuncaoG() + 1;
		}
                
		this.matriz = matriz; // SETA ATRIBUTO MATRIZ 

                //INICIA MEUS ATRIBUTOS
		lugarColunaVazia = -1;
		lugarLinhaVazia = -1;
		funcaoH = -1;
	}

	@Override
	public Collection<Estado> movimento() {
            
		if ((lugarColunaVazia == -1) || (lugarLinhaVazia == -1)) {
			procurarVazio(); // VE AONDE ESTA MEU ESPACO VAZIO PARA PODER FAZER O MOVIMENTO
		}

		// LISTA COM OS VALORES ENCONTRADOS 
		Collection<Estado> resultado = new ArrayList<Estado>();

		if (lugarLinhaVazia < 2) { // cima
			Integer[][] copia = MexeMatrizes.copiar(matriz);

			copia[lugarLinhaVazia + 1][lugarColunaVazia] = null;
			copia[lugarLinhaVazia][lugarColunaVazia] = matriz[lugarLinhaVazia + 1][lugarColunaVazia];

			resultado.add(new RealizaJogo(this, copia));
		}

		if (lugarLinhaVazia > 0) { // baixo 
			Integer[][] copia = MexeMatrizes.copiar(matriz);

			copia[lugarLinhaVazia - 1][lugarColunaVazia] = null;
			copia[lugarLinhaVazia][lugarColunaVazia] = matriz[lugarLinhaVazia - 1][lugarColunaVazia];

			resultado.add(new RealizaJogo(this, copia));
		}

		if (lugarColunaVazia < 2) { // esquerda
			Integer[][] copia = MexeMatrizes.copiar(matriz);

			copia[lugarLinhaVazia][lugarColunaVazia + 1] = null;
			copia[lugarLinhaVazia][lugarColunaVazia] = matriz[lugarLinhaVazia][lugarColunaVazia + 1];

			resultado.add(new RealizaJogo(this, copia));
		}

		if (lugarColunaVazia > 0) { // direita
			Integer[][] copia = MexeMatrizes.copiar(matriz);

			copia[lugarLinhaVazia][lugarColunaVazia - 1] = null;
			copia[lugarLinhaVazia][lugarColunaVazia] = matriz[lugarLinhaVazia][lugarColunaVazia - 1];

			resultado.add(new RealizaJogo(this, copia));
		}

		// Retornar resultado
		return resultado;
	}

	@Override
	public String toString() {
		return MexeMatrizes.print(matriz);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (Integer[] m : matriz) {
			result = prime * result + Arrays.hashCode(m);
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof RealizaJogo)) {
			return false;
		}

		RealizaJogo other = (RealizaJogo) obj;

		if (!MexeMatrizes.equals(matriz, other.matriz)) {
			return false;
		}

		return true;
	}

	@Override
	public int compareTo(Estado o) {
		if (!(o instanceof RealizaJogo)) {
			return 1;
		}

		RealizaJogo other = (RealizaJogo) o;

		return getFuncaoF() - other.getFuncaoF();
	}
	private void procurarVazio() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == null) { // Celula encontrada
					lugarLinhaVazia = i; // Armazenar linha
					lugarColunaVazia = j; // Armazenar coluna
					return;
				}
			}
		}
	}

	public Integer[][] getMatriz() {
		return matriz;
	}

	public int getLugarColunaVazia() { // valor do atributo posColunaVazio
		if (lugarColunaVazia == -1) {
			procurarVazio();
		}

		return lugarColunaVazia;
	}

	public int getLugarLinhaVazia() { // valor do atributo posLinhaVazio
		if (lugarLinhaVazia == -1) {
			procurarVazio();
		}

		return lugarLinhaVazia;
	}

	public RealizaJogo getPai() { // valor do atributo pai 
		return pai;
	}

	public int getFuncaoG() {
		return funcaoG;
	}

	public int getFuncaoH() {
		return funcaoH;
	}

	@Override
	public int getFuncaoF() {
		return funcaoH + funcaoG;
	}

	public void setFuncaoG(int funcaoG) {
		this.funcaoG = funcaoG;
	}

	public void setFuncaoH(int funcaoH) { // novo valor para funcao de H 
		this.funcaoH = funcaoH;
	}

}