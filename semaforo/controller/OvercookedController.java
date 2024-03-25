/*Existem diversos jogos de simulação. Um deles simula a participação de cozinheiros em
uma cozinha profissional realizando pratos. Numa das fases, o cozinheiro precisa
realizar o cozimento de 5 pratos simultâneos, onde cada cozimento não depende da
interação do jogador. Pratos de ID ímpar, são chamados de Sopa de Cebola e levam de
0,5 a 0,8 segundos para ficar prontos. Pratos de ID par, são chamados de Lasanha a
Bolonhesa e levam de 0,6 a 1,2 segundos para ficar prontos. Quando um prato inicia, é
necessário comunicar, em console, que se iniciou e, a cada 0,1 segundos, deve-se exibir
o percentual de cozimento (O percentual é definido pelo tempo total dividido por 0,1
segundos). Quando um prato fica pronto, é necessário comunicar em console o final e
fazer a entrega, que leva 0,5 segundos. O jogador só pode entregar um prato por vez e
deve comunicar a entrega. Simular a situação em Java.*/
package semaforo.controller;

import java.util.concurrent.Semaphore;

public class OvercookedController extends Thread {
	private int id;
	private Semaphore semaforoCozimento;
	private Semaphore semaforoEntrega;
	private boolean ePar;
	private String name;
	private static int bocasLivres = 5;

	public OvercookedController(int id, Semaphore semaforoCozimento, Semaphore semaforoEntrega) {
		this.id = id;
		this.semaforoCozimento = semaforoCozimento;
		this.semaforoEntrega = semaforoEntrega;
		this.ePar = (id % 2 == 0);
		if (this.ePar) {
			this.name = "Lasanha a Bolonhesa";
		} else {
			this.name = "Sopa de Cebola";
		}
	}

	@Override
	public void run() {
		System.out.printf("#%02d - PEDIDO RECEBIDO\n", id);
		try {
			semaforoCozimento.acquire();
			cozinhar();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforoCozimento.release();
		}
		try {
			semaforoEntrega.acquire();
			entregar();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforoEntrega.release();

		}

	}

	private void cozinhar() {
		bocasLivres--;
		System.out.printf("#%02d - Iniciou-se o cozimento do prato %s. %d livres\n", id, name, bocasLivres);
		int tempoTotal = 0;
		if (ePar) {
			tempoTotal = (int) ((Math.random() * 601) + 600);
		} else {
			tempoTotal = (int) ((Math.random() * 301) + 500);
		}
		int contaTempo = 0;
		int incremento = tempoTotal / 100;
		while (contaTempo < tempoTotal) {
			contaTempo += incremento;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
//			System.out.printf("#%02d - %s está %.1f%% concluído\n", id, name, ((double) contaTempo / tempoTotal) * 100);
			updateProgressBar(contaTempo, tempoTotal, name);
		}
		bocasLivres++;
		System.out.printf("\n#%02d - %s FINALIZADO. %d livres\n", id, name, bocasLivres);
	}

	private void entregar() {
		int tempo = 500;
		System.out.printf("\n#%02d -  %s ENTREGANDO\n", id, name);
		try {
			sleep(tempo);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.printf("\n#%02d - %s ENTREGA FINALIZADA\n", id, name);
	}

	private void updateProgressBar(int cur, int tot, String prato) {
		int progress = (int) ((double) cur / tot * 50);
		String progressBar = "[" + "=".repeat(progress) + " ".repeat(50 - progress) + "]";
//		System.out.print("\r" + progressBar + " " + ((double) cur / tot) * 100 + " - " + prato);
		System.out.printf("\r#%02d %s %.1f%% - %s\n", id, progressBar, ((double) cur / tot) * 100, prato);
	}

}
