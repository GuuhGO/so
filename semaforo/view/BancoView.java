package semaforo.view;

import semaforo.controller.BancoController;

public class BancoView {

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			int num = (int) ((Math.random() * 9000) + 1000);
			int saldo = (int) ((Math.random() * 1001) + 500);
			int valor = (int) ((Math.random() * 601) + 200);
			int tipo = (int) (Math.random() * 2);
			BancoController bancoController = new BancoController(num, saldo, valor, tipo);
			bancoController.start();
		}
	}
}
