package semaforo.controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {
	private int idCarro;
	private static int posChegada;
	private static int posSaida;
	private Semaphore semaforo;

	public ThreadCarro(int idCarro, Semaphore semaforo) {
		super();
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		carroAndando();
// 		--------------------- INÍCIO SEÇÃO CRÍTICA -------------------------
		try {
			semaforo.acquire();
			carroEstacionado();
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
//		--------------------- FIM SEÇÃO CRÍTICA -------------------------
			carroSaindo();
		}

	}

	private void carroAndando() {
		int distTotal = (int) ((Math.random() * 1001) + 1000);
		int distPercorrida = 0;
		int deslocamento = 100;
		int tempo = 1000;
		while (distPercorrida < distTotal) {
			distPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			System.out.printf("#%d já andou %dm.\n", idCarro, distPercorrida);
		}
		posChegada++;
		System.out.printf("#%d foi o %dº a chegar\n", idCarro, posChegada);
	}

	private void carroEstacionado() {
		System.out.printf("#%d estacionou\n", idCarro);
		int tempo = (int) ((Math.random() * 2001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private void carroSaindo() {
		posSaida++;
		System.out.printf("#%d foi o %dº a sair\n", idCarro, posSaida);
	}
}