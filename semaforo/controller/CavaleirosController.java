package semaforo.controller;

import java.util.concurrent.Semaphore;

public class CavaleirosController extends Thread {
	final private static int distTotal = 2000;
	private int num;
	private int distPercorrida;
	private static int tocha = 1;
	private static int pedra = 1;
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
		while (distPercorrida < distTotal) {
			velocidade = (int) ((Math.random() * max) + min);
			distPercorrida += velocidade;
			try {
				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
}
