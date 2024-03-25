/*1) Um servidor com multiprocessamento recebe requisições que envolve realizar cálculos
e fazer transações com bancos de dados. Por ter uma quantidade grande de núcleos de
processamentos e threads, além de um bom algoritmo de escalonamento de threads,
enquanto as threads fazem cálculos, estes podem ocorrer simultaneamente, mas
quando se faz a transação no banco de dados, esta deve ser apenas uma thread por
vez. Considere um conjunto de threads com IDs definidas na própria aplicação com
números iniciando em 1 e incrementando de um em um. As threads tem comportamento
como segue:
a) Threads com ID dividido por 3 resultando em resto igual a um fazem as transações:
- Cálculos de 0,2 a 1,0 segundos
- Transação de BD por 1 segundo
- Cálculos de 0,2 a 1,0 segundos
- Transação de BD por 1 segundo
b) Threads com ID dividido por 3 resultando em resto igual a dois fazem as transações:
- Cálculos de 0,5 a 1,5 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 0,5 a 1,5 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 0,5 a 1,5 segundos
- Transação de BD por 1,5 segundo
c) Threads com ID dividido por 3 resultando em resto igual a zero fazem as transações:
- Cálculos de 1 a 2 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 1 a 2 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 1 a 2 segundos
- Transação de BD por 1,5 segundo
Faça uma aplicação em Java que simule a situação de 21 Threads simultâneas, com
exibição em console de cada passo que a Thread está realizando.*/
package semaforo.controller;

import java.util.concurrent.Semaphore;

public class TransactionsController extends Thread {
	private int id;
	private Semaphore semaforo;
	private int resto;
	private int operacoes = 0;

	public TransactionsController(int id, Semaphore semaforo) {
		super();
		this.id = id;
		this.semaforo = semaforo;
		this.resto = id % 3;
	}

	@Override
	public void run() {

		try {
			calculos();
			//
			semaforo.acquire();
			transacao();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
			calculos();
		}

		try {
			semaforo.acquire();
			transacao();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}

		if (resto == 2 || resto == 3) {
			try {
				calculos();
				semaforo.acquire();
				transacao();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				semaforo.release();
			}
		}
		System.out.printf("#%02d - FINALIZOU TODAS AS ATIVIDADES\n", id);
	}

	private void calculos() {
		int tempoCalc = 0;

		switch (resto) {
		case 1:
			tempoCalc = (int) ((Math.random() * 801) + 200);
			break;
		case 2:
			tempoCalc = (int) ((Math.random() * 1001) + 500);
			break;
		case 0:
			tempoCalc = (int) ((Math.random() * 1001) + 1000);
			break;
		default:
			System.out.println("ID INVÁLIDO");
			break;
		}
		System.out.printf("#%02d - Iniciou os CÁLCULOS\n", id);
		try {
			sleep(tempoCalc);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		operacoes++;
		System.out.printf("#%02d - Finalizou os CÁLCULOS (%dms) - Total: %d operações\n", id, tempoCalc, operacoes);

	}

	private void transacao() {
		int tempoTransaction = 0;
		switch (resto) {
		case 1:
			tempoTransaction = 1000;
			break;
		case 2:
			tempoTransaction = 1500;
			break;
		case 0:
			tempoTransaction = 1500;
			break;
		default:
			System.out.println("ID INVÁLIDO");
			break;
		}
		System.out.printf("#%02d - Iniciou a TRANSAÇÃO\n", id);
		try {
			sleep(tempoTransaction);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		operacoes++;
		System.out.printf("#%02d - Finalizou a TRANSAÇÃO (%dms) - Total: %d operações\n", id, tempoTransaction,
				operacoes);
	}

}