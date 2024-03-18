/*2) Fazer uma aplicação que insira números aleatórios em uma matriz 3 x 5 e tenha 3
chamadas de Threads, onde cada uma calcula a soma dos valores de cada linha,
imprimindo a identificação da linha e o resultado da soma.
Dica: A main deve gerar uma matriz com números aleatórios, mas a Thread recebe um vetor
(uma linha da matriz)*/
package thread.controller;

public class E02_Thread_Controller extends Thread {
	private int row[];
	private int rowId;

	public E02_Thread_Controller(int row[], int rowId) {
		this.row = row;
		this.rowId = rowId;
	}

	@Override
	public void run() {
		int res = somaValores();
	}

	private int somaValores() {
		int soma = 0;
		int totalCol = row.length;
		System.out.printf("Linha %d: ", rowId);
		for (int col = 0; col < totalCol; col++) {
			soma += row[col];
			System.out.printf("%2d", row[col]);
			if (col < totalCol - 1) {
				System.out.print(" + ");
			}
		}
		System.out.printf(" = %d\n", soma);
		return soma;
	}

}