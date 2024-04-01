package semaforo.controller;

import java.util.concurrent.Semaphore;

public class Formula1Controller extends Thread {
	private static int distPista = 7000;
	private static int corredores = 0;
	private static Formula1Controller all[] = new Formula1Controller[14];

	private int voltas = 0;
	private int distPercorrida = 0;
	private int tempo = 0;
	private int velocidade;
	private int num;
	private String escuderia;
	private Semaphore semaforoEscuderia;
	private Semaphore semaforoPista;

	public Formula1Controller(int num, String escuderia, Semaphore semaforoPista, Semaphore semaforoEscuderia) {
		super();
		this.num = num;
		this.semaforoPista = semaforoPista;
		this.escuderia = escuderia;
		this.semaforoEscuderia = semaforoEscuderia;
		all[num] = this;
	}

	@Override
	public void run() {

		try {
			semaforoEscuderia.acquire();
			semaforoPista.acquire();
			correr();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			semaforoPista.release();
			semaforoEscuderia.release();
		}

		if (corredores == 14) {
			rank();
		}
	}

	private void rank() {
		int length = all.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - 1; j++) {
				if (all[j].tempo > all[j + 1].tempo) {
					Formula1Controller aux = all[j];
					all[j] = all[j + 1];
					all[j + 1] = aux;
				}
			}
		}

		System.out.println();
		int pos = 1;
		for (Formula1Controller atleta : all) {
			System.out.printf("%02dº lugar - Corredor #%02d - %-30s: %.3fs\n", pos, atleta.num, atleta.escuderia, (double) atleta.tempo / 1000);
			pos += 1;
		}
	}

	private void correr() {
		System.out.printf("Carro #%02d da escuderia [%-30s] INCIOU a corrida\n", num, escuderia);
		int contador = 0;
		int timeWait = 10;
		while (voltas < 3) {
			velocidade = (int) ((Math.random() * 18) + 66);
			distPercorrida += velocidade;
			contador++;
			if (contador % 10 == 0) {
				System.out.printf("Carro #%02d da escuderia [%-30s] PERCORREU %dm da %dª volta a %dm/s\n", num,
						escuderia, distPercorrida, voltas + 1, velocidade);
			}
			try {
				sleep(timeWait);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			tempo += timeWait;
			if (distPercorrida >= distPista) {
				System.out.printf("Carro #%02d da escuderia [%-30s] COMPLETOU a %dª volta\n", num, escuderia,
						voltas + 1);
				voltas += 1;
				distPercorrida = 0;
			}
		}
		corredores += 1;
		System.out.printf("Carro #%02d da escuderia [%-30s] FINALIZOU o circuito em %.2fms\n", num, escuderia,
				(double) tempo / 1000);

	}
}