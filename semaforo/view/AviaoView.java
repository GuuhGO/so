package semaforo.view;

import java.util.concurrent.Semaphore;

import semaforo.controller.AviaoController;

public class AviaoView {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2);
		for (int i = 0; i < 12; i++) {
			Thread aviaoController = new AviaoController(i + 1, semaphore);
			aviaoController.start();
		}
	}
}
