package primeira_aula.view;

import java.util.Scanner;

import primeira_aula.controller.E02_Controller;

public class E02_View {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		E02_Controller checkText = new E02_Controller();
		int count;

		
		for (int i = 0; i < 3; i++) {
			System.out.print("Entre com o texto: ");
			String in_text = scanner.next();
			count = checkText.count_parts(in_text, ";");
			System.out.printf("O texto possui %d partes\n\n", count);
		}
		scanner.close();
		
	}

}
