/*3) Criar em java um projeto com uma Thread chamada ThreadVetor, que receba um 
valor numérico e vetor como parâmetros. Caso o valor numérico seja par, a thread deve 
percorrer o vetor utilizando uma estrutura for (int i = 0 ; i < vet.length; i++) e contar o 
tempo para percorrer o vetor. Caso o valor numérico seja ímpar, a thread deve percorrer 
o vetor utilizando uma estrutura foreach e contar o tempo para percorrer o vetor. No 
final, ambas as possibilidades devem apresentar o tempo em segundos.
A operação main deve gerar um vetor de 1000 posições com valores aleatórios de 1 a 
100. Deve iniciar 2 ThreadVetor e para uma passar o número 1 e o vetor e para a outra, 
passar o número 2 e o mesmo vetor*/
package thread.controller;

public class ThreadVetor extends Thread{
	int vet[];
	int n;
	public ThreadVetor(int n, int vet[]) {
		this.n = n;
		this.vet = vet;
	}
	
	@Override
	public void run() {
		double start_time;
		double end_time;
		double total_time;
		if (n % 2 == 0) {
			start_time = System.nanoTime();
			for (int i = 0; i < vet.length; i++);
			end_time = System.nanoTime();
			total_time = (end_time - start_time) / Math.pow(10, 6);
			System.out.printf("For Length levou %f milissegundos\n", total_time);
		} else {
			start_time = System.nanoTime();
			for (int i : vet);
			end_time = System.nanoTime();
			total_time = (end_time - start_time) / Math.pow(10, 6);
			System.out.printf("For Each levou %f milissegundos\n", total_time);
		}
	}
}
