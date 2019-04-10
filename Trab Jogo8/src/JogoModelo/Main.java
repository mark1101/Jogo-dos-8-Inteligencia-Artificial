package JogoModelo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import JogoModelo.Busca;
import JogoModelo.Estado;
import JogoModelo.Profundidade;
import JogoModelo.BuscaEstrelaFH;
import JogoModelo.RealizaJogo;
import JogoModelo.MexeMatrizes;

/**
 * @author Mark
 */

public class Main {
        public int glob = 0;
	public static void main(String[] args) {
		Busca buscaEscolhida = Main.mostrarMenu();
		Main.executarBusca(buscaEscolhida);
	}

	private static Busca mostrarMenu() {
		
            
		System.out.println("Opcao de busca");
		System.out.println("1) Busca cega (feita com profundidade)");
		System.out.println("2) Heuristica normal ");
                System.out.println("3) Usando a distancia de Manhattan");
		

		int escolha = Main.lerNumero();

		// Criar um objeto do tipo Busca de acordo com a escolha
		Busca buscaEscolhida = Main.criarBusca(escolha);

		while (buscaEscolhida == null) {
			System.out.println("Busca nao encontrada. Tente novamente");
			escolha = Main.lerNumero();
			buscaEscolhida = Main.criarBusca(escolha);
		}
		return buscaEscolhida;
	}

                private static Busca criarBusca(int escolha) {
		switch (escolha) {
		case 1: 
			return new Profundidade(new RealizaJogo(null), new RealizaJogo(null, MexeMatrizes.criarEstadoFinal()));
		case 2: 
			return new BuscaEstrelaFH(new RealizaJogo(null), new RealizaJogo(null, MexeMatrizes.criarEstadoFinal()));
                case 3: 
			return new HeuristicaManhattan(new RealizaJogo(null), new RealizaJogo(null, MexeMatrizes.criarEstadoFinal()));
		default:
			return null;
		}
	}
	private static int lerNumero() {
		Scanner scanner = new Scanner(System.in);

		String escolha;

		int resultado;

		try {
			System.out.print("digite o numero : ");
			escolha = scanner.nextLine(); 

			if (escolha.isEmpty()) {
				throw new Exception("Erro");
			}

			if (escolha.equalsIgnoreCase("exit")) {
				System.exit(0);
			}

			resultado = Integer.parseInt(escolha);

		} catch (Exception e) {
			System.out.println("Valor invalido. Tente novamente");
			resultado = Main.lerNumero(); 
		}
		System.out.println();
		return resultado;
	}

	
	private static void executarBusca(Busca busca) { // metodo que inicia minha busca dependendo da minha escolha 
		System.out.println("Estado inicial:");
		System.out.println(busca.getEstadoInicial());
		System.out.println("Estado final:");
		System.out.println(busca.getEstadoFinal());

		System.out.println("Rodando__");

		long inicio = System.currentTimeMillis();

		Estado resultado = busca.executar();

		long fim = System.currentTimeMillis();

		System.out.println();
		System.out.println("Finalizado!");

		// Mostrar detalhes
		Main.mostrarDetalhes(busca, resultado, (fim - inicio));

		if (resultado != null) { // Se o resultado foi encontrado
			System.out.println("CAMINHO DE SUCESSO ENCONTRADO");
                        try { Thread.sleep (1000); mostrarCaminho(resultado); } catch (InterruptedException ex) {}
		}
                if (resultado == null )
                {
                    System.out.println("NAO ACHEI O RESULTADO DE INICIO");
                    System.out.println("CHAMANDO NOVAMENTE O PROGRAMA");
                    System.out.println("");
                    Busca buscaEscolhida = Main.mostrarMenu(); 
                    Main.executarBusca(buscaEscolhida);
                    
                }
	}

	private static void mostrarDetalhes(Busca busca, Estado resultado, long tempoTotal) {
		System.out.println("---------- RESULTADOS DA MINHA BUSCAA ----------");

		// Tipo
		System.out.println("BUSCA : " + busca);

		// Resultado
		System.out.println("RESULTADO :  " + (resultado != null ? "Encontado" : "Nao encontrado"));

		//Tempo total
		System.out.printf("Tempo total: %,d ms%n", tempoTotal);

		// Tamanho das listas abertas e fechadas
		System.out.println("Lista aberta: " + Main.mostrarTamanhoLista(busca.getListaAberta()));
		System.out.println("Lista fechada: " + Main.mostrarTamanhoLista(busca.getListaFechada()));

                System.out.println("");
                System.out.println("");
        }
	private static void mostrarCaminho(Estado resultado) {
		Estado e = resultado; // Variavel auxiliar

		// Lista contendo o caminho
		List<Estado> listaCaminho = new LinkedList<Estado>();

		// Adicionar o caminho na lista
		while (e != null) {
			listaCaminho.add(0, e);
			e = e.getPai();
		}

		int passos = listaCaminho.size();

		// Imprimir caminho
		for (Estado estado : listaCaminho) {
			System.out.println(estado);
		}

		// Imprimir total de passos encontrado pela busca
		System.out.println("\nTotal de passos: " + passos);
	}
        
        //FUNCAO AONDE MOSTRO O TAMANHO DAS MINHAS LISTAS
	private static String mostrarTamanhoLista(Collection<Estado> lista) {
		
		int tamanhoLista = lista.size();

		if (tamanhoLista > 0) {
			if (tamanhoLista == 1) {
				return "1 elemento";
			} else {
				return String.format("%,d elementos", tamanhoLista);
			}
		} else {
			return "Vazia";
		}
	}
}