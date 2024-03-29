package semaforo.controller;

import java.util.concurrent.Semaphore;

public class CavaleirosController extends Thread {
	final private static int distTotal = 2000;
	private int num;
	private int distPercorrida;
	private static int tocha = 1;
	private static int pedra = 1;
	private final static int saida = 3; 
	private static int doors[] = { 1, 2, 3, 4 };
	private int velocidade;
	private int min = 2;
	private int max = 3;
	private Semaphore semaforo;

	public CavaleirosController(int num, Semaphore semaforo) {
		super();
		this.semaforo = semaforo;
		this.num = num;
	}

	@Override
	public void run() {
		int contador = 0;
		while (distPercorrida < distTotal) {
			velocidade = (int) ((Math.random() * max) + min);
			distPercorrida += velocidade;
			contador += 1;
			try {
				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (contador % 50 == 0)
				System.out.printf("Cavaleiro #%02d andou %dm\n", num, distPercorrida);

			if (distPercorrida >= 500 && tocha == 1) {
				try {
					semaforo.acquire();
					pickUpTorch();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}

			if (distPercorrida >= 1500 && pedra == 1) {
				try {
					semaforo.acquire();
					pickUpStone();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		}
		System.out.printf("Cavaleiro #%02d CHEGOU\n", num);

		int porta = 0;
		try {
			semaforo.acquire();
			porta = randomDoor();
			System.out.printf("Cavaleiro #%02d abriu a porta %d\n", num, porta);

			if (porta != saida) {
				System.out.printf("Cavaleiro #%02d foi DEVORADO\n", num);
			} else {
				System.out.printf("Cavaleiro #%02d SAIU\n", num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

	private void pickUpStone() {
		pedra--;
		min += 2;
		max += 2;
		System.out.printf("Cavaleiro #%02d PEGOU A PEDRA\n", num);
	}

	private void pickUpTorch() {
		tocha--;
		max += 2;
		min += 2;
		System.out.printf("Cavaleiro #%02d PEGOU A TOCHA\n", num);
	}

	private static int randomDoor() {
		int pos;
		int valor = 0;
		do {
			pos = (int) (Math.random() * 4);
			valor = doors[pos];
			if (valor != -1) {
				doors[pos] = -1;
				break;
			}
		} while (doors[pos] == -1);
		return valor;
	}
}
