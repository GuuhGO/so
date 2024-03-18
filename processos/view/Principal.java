package processos.view;

import processos.controller.ProcessosController;

public class Principal {

	public static void main(String[] args) {
		ProcessosController procCon = new ProcessosController();
		String info = procCon.os();
		System.out.println(info);


		// String process = "C:\\Windows\\write.exe";
		// procCon.callProcess(process);
		
		// String process = "C:\\Windows\\regedit.exe";
		// procCon.callProcess(process);

		// String process = "TASKLIST /FO TABLE";
		// procCon.readProcess(process);

		// String process = "PING -t10 www.google.com.br";
		// procCon.readProcess(process);

		// String process = "TRACERT www.uol.com.br";
		// procCon.readProcess(process);

		// String process = "ipconfig";
		// procCon.readProcess(process);
		
		// String param = "cmd.exe";
		// procCon.killProcess(param);
		
		// String param = "4208";
		// procCon.killProcess(param);

	}
}
