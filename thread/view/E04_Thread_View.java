/*3) Criar em java um projeto com uma Thread chamada ThreadVetor, que receba um 
valor num�rico e vetor como par�metros. Caso o valor num�rico seja par, a thread deve 
percorrer o vetor utilizando uma estrutura for (int i = 0 ; i < vet.length; i++) e contar o 
tempo para percorrer o vetor. Caso o valor num�rico seja �mpar, a thread deve percorrer 
o vetor utilizando uma estrutura foreach e contar o tempo para percorrer o vetor. No 
final, ambas as possibilidades devem apresentar o tempo em segundos.
A opera��o main deve gerar um vetor de 1000 posi��es com valores aleat�rios de 1 a 
100. Deve iniciar 2 ThreadVetor e para uma passar o n�mero 1 e o vetor e para a outra, 
passar o n�mero 2 e o mesmo vetor*/
package thread.view;

import thread.controller.ThreadSapo;

public class E04_Thread_View {
	public static void main(String[] args) {
		String nomes[] = { "A", "B", "C", "D", "E" };
		for (int i = 0; i < 5; i++) {
			Thread t = new ThreadSapo(20, nomes[i]);
			t.start();
		}
	}
}
