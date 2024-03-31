package semaforo.controller;

import java.util.concurrent.Semaphore;

public class AviaoController extends Thread {
	private int num;
	private Semaphore semaforo;
	private String pista;
	private static String pistas[] = { "Norte", "Sul" };

	public AviaoController(int num, Semaphore semaforo) {
		super();
		this.num = num;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		try {
			semaforo.acquire();
			manobrar();
			taxiar();
			decolagem();
			afastamento();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void manobrar() throws InterruptedException {
		pista = ocuparPista();
		System.out.printf("Pista %s OCUPADA pelo Avião #%02d\n", pista, num);
		System.out.printf("Avião #%02d está MANOBRANDO\n", num);
		int duracao = (int) ((Math.random() * 401) + 300);
		sleep(duracao);
		System.out.printf("Avião #%02d terminou de MANOBRAR\n", num);
	}

	private void taxiar() throws InterruptedException {
		System.out.printf("Avião #%02d está TAXIANDO\n", num);
		int duracao = (int) ((Math.random() * 501) + 500);
		sleep(duracao);
		System.out.printf("Avião #%02d terminou de TAXIAR\n", num);
	}

	private void decolagem() throws InterruptedException {
		System.out.printf("Avião #%02d está realizando a DECOLAGEM\n", num);
		int duracao = (int) ((Math.random() * 201) + 600);
		sleep(duracao);
		System.out.printf("Avião #%02d finalizou a DECOLAGEM\n", num);
	}

	private void afastamento() throws InterruptedException {
		System.out.printf("Avião #%02d está realizando o AFASTAMENTO DA ÁREA\n", num);
		int duracao = (int) ((Math.random() * 501) + 300);
		sleep(duracao);
		System.out.printf("Avião #%02d finalizou o AFASTAMENTO DA ÁREA\n"
			+ "Pista %s LIBERADA pelo Avião #%02d\n", num, pista, num);
		liberarPista(pista);
	}

	private String ocuparPista() {
		int pos;
		String valor = "";
		do {
			pos = (int) (Math.random() * 2);
			valor = pistas[pos];
			if (!valor.equals("")) {
				pistas[pos] = "";
				break;
			}
		} while (valor.equals(""));
		return valor;
	}

	private void liberarPista(String pistaOcupada) {
		if (pistaOcupada.equals("Norte")) {
			pistas[0] = pistaOcupada;
		} else if (pistaOcupada.equals("Sul")) {
			pistas[1] = pistaOcupada;
		}
	}
}
