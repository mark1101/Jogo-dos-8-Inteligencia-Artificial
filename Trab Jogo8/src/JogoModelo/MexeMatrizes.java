package JogoModelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * @author Mark  
 */
public class MexeMatrizes {

    public static Integer[][] criarEstadoFinal() {
        Integer[][] estadoFinal = {{1, 2, 3}, {8, null, 4}, {7, 6, 5}};
        return estadoFinal;
    }

    public static Integer[][] gerarMatrizRandomica() {
        List<Integer> lugarM = new ArrayList<Integer>();
        
        lugarM.add(null);
        lugarM.add(1);
        lugarM.add(2);
        lugarM.add(3);
        lugarM.add(4);
        lugarM.add(5);
        lugarM.add(6);
        lugarM.add(7);
        lugarM.add(8);
       

        Collections.shuffle(lugarM);

        Integer[] matriz = lugarM.toArray(new Integer[lugarM.size()]); // mudo minha lista para array 

        // Criar matriz
        Integer[][] resultado = new Integer[][]{{matriz[0], matriz[1], matriz[2]}, {matriz[3], matriz[4], matriz[5]}, {matriz[6], matriz[7], matriz[8]}};

        return resultado;
    }

    public static String print(Integer[][] matriz) {
        if (matriz == null) {
            throw new IllegalArgumentException("matriz nula!");
        }
        StringBuilder stringBuilder = new StringBuilder();

       //concatenacao
        stringBuilder.append("+---+---+---+");
        stringBuilder.append("\n");
        stringBuilder.append(String.format("| %1s | %1s | %1s |", MexeMatrizes.transformarCelula(matriz[0][0]), MexeMatrizes.transformarCelula(matriz[0][1]), MexeMatrizes.transformarCelula(matriz[0][2])));
        stringBuilder.append("\n");
        stringBuilder.append("+---+---+---+");
        stringBuilder.append("\n");
        stringBuilder.append(String.format("| %1s | %1s | %1s |", MexeMatrizes.transformarCelula(matriz[1][0]), MexeMatrizes.transformarCelula(matriz[1][1]), MexeMatrizes.transformarCelula(matriz[1][2])));
        stringBuilder.append("\n");
        stringBuilder.append("+---+---+---+");
        stringBuilder.append("\n");
        stringBuilder.append(String.format("| %1s | %1s | %1s |", MexeMatrizes.transformarCelula(matriz[2][0]), MexeMatrizes.transformarCelula(matriz[2][1]), MexeMatrizes.transformarCelula(matriz[2][2])));
        stringBuilder.append("\n");
        stringBuilder.append("+---+---+---+");
        stringBuilder.append("\n");

        // Retornar resultado
        return stringBuilder.toString();
    }

    private static String transformarCelula(Integer valorCelula) { // verificacao com mudanca para string, verificar estado nulo s
        if (valorCelula == null) {
            return ""; // String vazia
        } else {
            return valorCelula.toString(); // Transforma a celula em String
        }
    }

    public static Integer[][] copiar(Integer[][] matriz) { // gera uma copia da minha matriz 

        return new Integer[][]{{matriz[0][0], matriz[0][1], matriz[0][2]}, {matriz[1][0], matriz[1][1], matriz[1][2]}, {matriz[2][0], matriz[2][1], matriz[2][2]}};
    }

    public static boolean equals(Integer[][] matriz1, Integer[][] matriz2) { // compara minhas matrizes 
        if (matriz1 == matriz2) {
            return true;
        }

        if (matriz1.length != matriz2.length) {
            return false;
        }

        for (int i = 0; i < matriz1.length; i++) {
            if (!(Arrays.equals(matriz1[i], matriz2[i]))) {
                return false;
            }
        }

        return true;
    }

}
