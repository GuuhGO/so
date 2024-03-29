package semaforo.view;

import java.util.concurrent.Semaphore;

import semaforo.controller.CavaleirosController;

public class CavaleirosView {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		for (int i = 0; i < 4; i++) {
			CavaleirosController cavaleirosController = new CavaleirosController(i, semaphore);
			cavaleirosController.start();
		}
	}
}