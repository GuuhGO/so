package semaforo.view;

import java.util.concurrent.Semaphore;
import semaforo.controller.ThreadCarro;

public class Estacionamento {
	public static void main(String[] args) {
		int permissoes = 3;
		Semaphore semaphore = new Semaphore(permissoes);
		for (int idCarro = 0; idCarro < 10; idCarro++) {
			Thread threadCarro = new ThreadCarro(idCarro, semaphore);
			threadCarro.start();
		}
	}
}