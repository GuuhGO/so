package semaforo.controller;

import java.util.concurrent.Semaphore;

public class TriatloC extends Thread {
	private static int totalCorrida = 3000;
	private static int totalCiclismo = 5000;
	private static int colocacao = 0;
	private static int armas = 5;
	private static TriatloC atletas[] = new TriatloC[25];
	private static Semaphore sem_chegada = new Semaphore(1);

	private Semaphore sem_tiroAoAlvo;
	public int num;
	private int velocidade = 0;
	private int percCorrida = 0;
	private int percCiclismo = 0;
	public int pontuacao = 0;

	public TriatloC(int num, Semaphore semaforo) {
		super();
		this.num = num + 1;
		this.sem_tiroAoAlvo = semaforo;
		atletas[num] = this;
	}

	@Override
	public void run() {
		try {
			corrida();

			sem_tiroAoAlvo.acquire();
			tiroAoAlvo();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem_tiroAoAlvo.release();
			try {
				ciclismo();
				sem_chegada.acquire();
				chegar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				sem_chegada.release();
			}
		}

		if (colocacao >= 25) {
			rank();
		}
	}

	private void rank() {
		int length = atletas.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - 1; j++) {
				if (atletas[j].pontuacao < atletas[j + 1].pontuacao) {
					TriatloC aux = atletas[j];
					atletas[j] = atletas[j + 1];
					atletas[j + 1] = aux;
				}
			}
		}

		int pos = 1;
		for (TriatloC atleta : atletas) {
			System.out.printf("%02dº lugar - Atleta #%02d: %d pontos\n", pos, atleta.num, atleta.pontuacao);
			pos += 1;
		}
	}

	private void corrida() throws InterruptedException {
		System.out.printf("Alteta #%02d INICIOU a Corrida\n", num);
		velocidade = (int) ((Math.random() * 6) + 20);
		int tempo = 30;
		int contador = 0;
		while (percCorrida < totalCorrida) {
			percCorrida += velocidade;
			contador += 1;
			if (contador % 25 == 0)
				System.out.printf("Atleta #%02d percorreu %dm na corrida\n", num, percCorrida);
			sleep(tempo);
		}
		System.out.printf("Alteta #%02d TERMINOU a Corrida\n", num);
	}

	private void tiroAoAlvo() throws InterruptedException {
		armas--;
		System.out.printf("Atleta #%02d INICIOU o Tiro ao Alvo - Armas Livres: %d\n", num, armas);
		int tempo = (int) ((Math.random() * 2501) + 500);
		for (int tiro = 0; tiro < 3; tiro++) {
			int pontos = (int) (Math.random() * 11);
			pontuacao += pontos;
			System.out.printf("Atleta #%02d fez %d pontos no %dº tiro - Total de %d\n", num, pontos, tiro + 1,
					pontuacao);
			sleep(tempo);
		}
		armas++;
		System.out.printf("Atleta #%02d TERMINOU o Tiro ao Alvo - Armas Livres: %d\n", num, armas);
	}

	private void ciclismo() throws InterruptedException {
		System.out.printf("Atleta #%02d INICIOU o Ciclismo \n", num);
		velocidade = (int) ((Math.random() * 11) + 30);
		int tempo = 40;
		int contador = 0;
		while (percCiclismo < totalCiclismo) {
			percCiclismo += velocidade;
			contador += 1;
			if (contador % 25 == 0)
				System.out.printf("Atleta #%02d percorreu %dm no ciclismo\n", num, percCiclismo);
			sleep(tempo);
		}
		System.out.printf("Alteta #%02d TERMINOU o Ciclismo\n", num);
	}

	private void chegar() {
		colocacao += 1;
		int pontos = 260 - (10 * colocacao);
		pontuacao += pontos;
		System.out.printf("Atleta #%02d chegou em %dº lugar recebendo %d pontos - Total: %d\n", num, colocacao, pontos,
				pontuacao);
	}

}