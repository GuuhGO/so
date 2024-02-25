package primeira_aula.view;

import primeira_aula.controller.E01_Controller;

public class E01_View {
	public static void main(String[] args) {
		E01_Controller iter_con = new E01_Controller();
		int vet1k[] = new int[1000];
		int vet10k[] = new int[10000];
		int vet100k[] = new int[100000];
		System.out.printf("Teste com %d posições\n", vet1k.length);
		iter_con.iter_arr(vet1k);
		System.out.printf("Teste com %d posições\n", vet10k.length);
		iter_con.iter_arr(vet10k);
		System.out.printf("Teste com %d posições\n", vet100k.length);
		iter_con.iter_arr(vet100k);
		System.out.println("FIM DA EXECUÇÃO\n");
	}
}
