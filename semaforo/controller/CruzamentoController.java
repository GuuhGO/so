package semaforo.controller;

import java.util.concurrent.Semaphore;

public class CruzamentoController extends Thread {
	private int num;
	private String sentido;
	private Semaphore semaforo;

	public CruzamentoController(int num, Semaphore semaforo, String sentido) {
		super();
		this.num = num;
		this.semaforo = semaforo;
		this.sentido = sentido;
	}

	@Override
	public void run() {
		System.out.printf("Carro #%02d está AGUARDANDO PARA PASSAR no sentido %s\n", num, sentido);
		try {
			semaforo.acquire();
			passar();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} finally {
			semaforo.release();
		}

	}

	private void passar() {
		System.out.printf("Carro #%02d está passando no sentido %s\n", num, sentido);
		try {
			sleep(100);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.printf("Carro #%02d terminou a passagem\n", num);
	}

}
