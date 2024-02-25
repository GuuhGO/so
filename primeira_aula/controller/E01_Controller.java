package primeira_aula.controller;

public class E01_Controller {
	public E01_Controller() {
		super();
	}
	public void iter_arr(int arr[]) {
		double time_start = System.nanoTime();
		for (int i : arr);
		double time_end = System.nanoTime();
		double total_time = time_end - time_start;
		total_time = total_time / Math.pow(10, 9);
		System.out.printf("Tempo total: %f seg\n\n", total_time);
	}

}
