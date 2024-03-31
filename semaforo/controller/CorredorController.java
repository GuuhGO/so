package semaforo.controller;

import java.util.concurrent.Semaphore;

public class CorredorController extends Thread {
	private int num;
	private Semaphore semaforo;
	private int velocidade;
	private int distPercorrida = 0;

	private static int distTotal = 200;

	public CorredorController(int num, Semaphore semaforo) {
		super();
		this.num = num;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		andarCorredor();
		try {
			semaforo.acquire();
			cruzarPorta();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}

	private void andarCorredor() {
		while (distPercorrida < distTotal) {
			velocidade = (int) ((Math.random() * 3) + 4);
			try {
				sleep(1000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			distPercorrida += velocidade;
			System.out.printf("Pessoa %02d andou %dm\n", num, distPercorrida);
		}
		System.out.printf("Pessoa %02d chegou na porta\n", num);
	}

	private void cruzarPorta() {
		int tempo = (int) ((Math.random() * 2000) + 1000);
		try {
			sleep(tempo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.printf("Pessoa %02d cruzou a porta\n", num);
	}
}
