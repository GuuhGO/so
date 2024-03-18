package thread.controller;

public class ThreadId extends Thread {
	private int idThread;

	public ThreadId(int idThread) {
		this.idThread = idThread;
	}

	@Override
	public void run() {
		// Só é executado o que está aqui dentro deste bloco
		System.out.println(idThread);
	}

}