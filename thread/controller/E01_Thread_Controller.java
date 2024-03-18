/*1) Fazer uma aplicação que rode 5 Threads que cada uma delas imprima no console o
seu número (TID).
Dica: O número que deve ser impresso é a saída da operação int id = getId() da Thread.*/

package thread.controller;

public class E01_Thread_Controller extends Thread {
	private int id;

	public E01_Thread_Controller() {
		super();
	}

	@Override
	public void run() {
		this.id = (int) getId();
		System.out.printf("TID #%d\n", id);
	}

}
