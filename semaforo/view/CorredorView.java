package semaforo.view;

import java.util.concurrent.Semaphore;

import semaforo.controller.CorredorController;

public class CorredorView {

	public static void main(String[] args) {
		int permits = 1;
		Semaphore semaphore = new Semaphore(permits);
		for (int i = 0; i < 4; i++) {
			CorredorController cor = new CorredorController(i, semaphore);
			cor.start();
		}
	}
}