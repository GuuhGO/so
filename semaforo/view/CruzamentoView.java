package semaforo.view;

import java.util.concurrent.Semaphore;

import semaforo.controller.CruzamentoController;

public class CruzamentoView {

	public static void main(String[] args) {
		int limit = 1;
		String sentidos[] = { "CIMA", "BAIXO", "ESQUERDA", "DIREITA" };
		Semaphore semaphore = new Semaphore(limit);
		for (int i = 0; i < 25; i++) {
			int rdm = (int) (Math.random() * 4);
			CruzamentoController crz = new CruzamentoController(i, semaphore, sentidos[rdm]);
			crz.start();
		}
	}
}