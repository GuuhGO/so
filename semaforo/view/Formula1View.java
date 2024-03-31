package semaforo.view;

import java.util.concurrent.Semaphore;

import semaforo.controller.Formula1Controller;

public class Formula1View {

	public static void main(String[] args) {
		int escuderias[] = { 1, 2, 3, 4, 5, 6, 7 };
		String strEscuderias[] = { "Radiator Springs", "Equipe Turbo Racing", "Estrela do Asfalto Racing",
				"Flecha Veloz Racing", "Cavaleiros da Curva", "Equipe Relâmpago", "Pneu Quente" };
		Semaphore semaforoPista = new Semaphore(5);
		Semaphore semaforosEscuderias[] = new Semaphore[7];

		int length = semaforosEscuderias.length;
		for (int i = 0; i < length; i++) {
			semaforosEscuderias[i] = new Semaphore(1);
		}

		int cont = 0;
		for (int i = 0; i < 14; i++) {
			Formula1Controller f1c = new Formula1Controller(i, strEscuderias[cont], semaforoPista,
					semaforosEscuderias[cont]);
			f1c.start();
			if (i % 2 != 0) {
				cont++;
			}
		}
	}
}