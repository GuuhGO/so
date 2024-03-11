package processos.controller;

public class ProcessosController {
	public ProcessosController() {
		super();
	}

	// Retorna o S.O que está em execuçãp na máquina
	public String os() {
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		
		String output = String.format("S.O: %s - V. %s - Arch. %s", os, version, arch);
		return output;
	}
}
