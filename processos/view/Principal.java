package processos.view;

import processos.controller.ProcessosController;

public class Principal {

	public static void main(String[] args) {
		ProcessosController procCon = new ProcessosController();
		String info = procCon.os();
		System.out.println(info);
	}
}
