package primeira_aula.controller;

public class E02_Controller {
	public E02_Controller() {
		super();
	}
	public int count_parts(String text, String delimiter) {
		String vet[] = text.split(delimiter);
		int count = 0;
		for (String word : vet) {
			count++;
		}
		return count;
	}
}
