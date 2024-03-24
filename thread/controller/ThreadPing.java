/*5) No Sistema Operacional Linux, o comando para realizar uma operação de ping com 10
iterações é ping -4 -c 10 <servidor>. Fazer uma aplicação Java que rode 3 Thread em S.O.
Linux fazendo operação de ping para os servidores UOL (www.uol.com.br), Terra
(www.terra.com.br) e Google (www.google.com.br). Cada thread deve ler a saída do ping
imprimindo, a cada iteração, o nome do servidor (usar fixo: UOL, Terra ou Google) e o tempo
daquela iteração. Ao fim, deve-se exibir o nome do servidor (usar fixo: UOL, Terra ou Google)
e o tempo médio obtido pela operação. Outros Sistemas Operacionais devem ser
descartados.*/
package thread.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread {
	private String servidor;
	private String os;

	public ThreadPing(String servidor) {
		this.servidor = servidor;
		this.os = System.getProperty("os.name");
	}

	@Override
	public void run() {
		try {
			readProcess(servidor);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void callProcess(String server) throws Exception {
		if (!this.os.equals("Linux"))
			throw new Exception("Sistema Operacional inválido. Deve ser utilizado no linux");
		try {
			StringBuffer buffer = new StringBuffer();
			switch (server) {
			case "UOL":
				buffer = new StringBuffer("www.uol.com.br");
				break;
			case "Terra":
				buffer = new StringBuffer("www.terra.com.br");
				break;
			case "Google":
				buffer = new StringBuffer("www.google.com.br");
				break;
			default:
				System.out.println("OPÇÃO INVÁLIDA");
				break;
			}
			buffer.insert(0, "ping -4 -c 10 ");
			System.out.printf("Executando comando %s\n", buffer.toString());
			Runtime.getRuntime().exec(buffer.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void readProcess(String server) throws Exception {
		if (!this.os.equals("Linux"))
			throw new Exception("Sistema Operacional inválido. Deve ser utilizado no linux");
		try {
			StringBuffer bufferCmd = new StringBuffer();
			switch (server) {
			case "UOL":
				bufferCmd = new StringBuffer("www.uol.com.br");
				break;
			case "Terra":
				bufferCmd = new StringBuffer("www.terra.com.br");
				break;
			case "Google":
				bufferCmd = new StringBuffer("www.google.com.br");
				break;
			default:
				System.out.println("OPÇÃO INVÁLIDA");
				break;
			}
			bufferCmd.insert(0, "ping -4 -c 10 ");
			System.out.printf("Executando comando %s\n", bufferCmd.toString());

			Process p = Runtime.getRuntime().exec(bufferCmd.toString());

			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			double soma = 0f;
			int contador = 0;
			while (linha != null) {
				if (linha.contains("time=")) {
					String out[] = linha.split(" ");
					String ping = out[7].replace("time=", "");
					System.out.printf("Servidor %s: %sms\n", this.servidor, ping);
					soma += Double.parseDouble(ping);
					contador++;
				}
				linha = buffer.readLine();
			}
			double media = (double) soma / contador;
			System.out.printf("\nPing médio servidor %s: %.2fms\n", this.servidor, media);

			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
