package semaforo.view;

import java.util.Stack;
import java.util.concurrent.Semaphore;

import semaforo.controller.TriatloC;

public class TriatloView {
	public static <E> void main(String[] args) {
		Semaphore semaphore = new Semaphore(5);
		TriatloC atletas[] = new TriatloC[25];
		for (int num = 0; num < 25; num++) {
			TriatloC atleta = new TriatloC(num, semaphore);
			atletas[num] = atleta;
			atleta.start();
		}
	}
}