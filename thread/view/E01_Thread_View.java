/*1) Fazer uma aplicação que rode 5 Threads que cada uma delas imprima no console o
seu número (TID).
Dica: O número que deve ser impresso é a saída da operação int id = getId() da Thread.*/
package thread.view;

import thread.controller.E01_Thread_Controller;

public class E01_Thread_View {
	public static void main(String[] args) {
		for (int nThread = 0; nThread < 5; nThread++) {
			E01_Thread_Controller threadC = new E01_Thread_Controller();
			threadC.start();
		}
	}

}
