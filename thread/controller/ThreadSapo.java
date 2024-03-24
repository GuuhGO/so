/*4) Fazer uma aplica��o de uma corrida de sapos, com 5 Threads, cada Thread 
controlando 1 sapo. Deve haver um tamanho m�ximo para cada pulo do sapo (em 
metros) e a dist�ncia m�xima para que os sapos percorram. A cada salto, um sapo pode 
dar um salto de 0 at� o tamanho m�ximo do salto (valor aleat�rio). Ap�s dar um salto, a 
Thread, para cada sapo, deve mostrar no console, qual foi o tamanho do salto e quanto 
o sapo percorreu. Assim que o sapo percorrer a dist�ncia m�xima, a Thread deve 
apresentar que o sapo chegou e qual sua coloca��o.
Dica: O exerc�cio deve ser resolvido todo em console, ou seja, como se estivesse sendo 
narrado. Aten��o para a forma de definir a ordem de chegada.*/
package thread.controller;

public class ThreadSapo extends Thread {
	private String nome;
	private int maxSalto = 5;
	private int distTotal;
	private int distPercorrida = 0;
	private static int colocacao = 0;

	public ThreadSapo(int distTotal, String nome) {
		this.distTotal = distTotal;
		this.nome = nome;
	}

	@Override
	public void run() {
		while (distPercorrida < distTotal) {
			try {
				int salto = (int) (Math.random() * maxSalto) + 1;
				distPercorrida += salto;
				System.out.printf("Sapo %s saltou %d - Percorreu %d\n", this.nome, salto, distPercorrida);
				sleep(700);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		colocacao++;
		System.out.printf("Sapo %s chegou em %dº lugar\n", this.nome, colocacao);
	}

}