/*2) Fazer uma aplicação que insira números aleatórios em uma matriz 3 x 5 e tenha 3
chamadas de Threads, onde cada uma calcula a soma dos valores de cada linha,
imprimindo a identificação da linha e o resultado da soma.
Dica: A main deve gerar uma matriz com números aleatórios, mas a Thread recebe um vetor
(uma linha da matriz)*/
package thread.view;

import thread.controller.E02_Thread_Controller;

public class E02_Thread_View {

	public static void main(String[] args) {
		int mat[][] = new int[3][5];
		int totalRows = mat.length;
		mat = gerar_aleatorios(mat);
		for (int i = 0; i < totalRows; i++) {
			E02_Thread_Controller threadC = new E02_Thread_Controller(mat[i], i);
			threadC.start();
		}
	}

	private static int[][] gerar_aleatorios(int[][] mat) {
		int totalRows = mat.length;
		int totalColumns = mat[0].length;
		for (int row = 0; row < totalRows; row++) {
			for (int col = 0; col < totalColumns; col++) {
				mat[row][col] = (int) (Math.random() * 51);
				System.out.printf("%2d | ", mat[row][col]);
			}
			System.out.println();
		}
		System.out.println();
		return mat;
	}

}