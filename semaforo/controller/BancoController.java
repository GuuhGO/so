package semaforo.controller;

import java.util.concurrent.Semaphore;

public class BancoController extends Thread {
	private int num;
	private int saldo;
	private int valor;
	private static Semaphore semaforoSaque = new Semaphore(1);
	private static Semaphore semaforoDeposito = new Semaphore(1);
	private String transacao;

	private static String tiposTransacao[] = { "SAQUE", "DEPÓSITO" };

	public BancoController(int num, int saldo, int valor, int tipo) {
		super();
		this.num = num;
		this.saldo = saldo;
		this.valor = valor;
		this.transacao = tiposTransacao[tipo];
	}

	@Override
	public void run() {
		System.out.printf("Solicitação de %s de R$%d na conta %d - Saldo Atual: R$%d\n", transacao, this.valor, this.num, this.saldo);
		switch (transacao) {
		case "SAQUE":
			try {
				semaforoSaque.acquire();
				saque(this.valor);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			} finally {
				semaforoSaque.release();
			}
			break;
		case "DEPÓSITO":
			try {
				semaforoDeposito.acquire();
				deposito(this.valor);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				semaforoDeposito.release();
			}
			break;
		default:
			System.out.println("OPÇÃO INVÁLIDA");
			break;
		}
	}

	private void deposito(int valor2) {
		
		if (valor2 > 0) {
			try {
				sleep(500);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			this.saldo += valor2;
			System.out.printf("DEPÓSITO de R$%d na conta %d realizado com sucesso - Saldo atual: R$%d\n", valor2, this.num,
					this.saldo);
		} else {
			System.out.printf("Conta %d - Valor do DEPÓSITO não pode ser menor que 0\n", this.num);
		}

	}

	private void saque(int valor) {
		if (valor <= saldo && valor > 0) {
			try {
				sleep(500);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			this.saldo -= valor;
			System.out.printf("SAQUE de R$%d na conta %d realizado com sucesso - Saldo atual: R$%d\n", valor, this.num,
					this.saldo);
		}

		else {
			System.out.printf("Não foi possível realizar o SAQUE de R$%d da conta %d - Saldo insuficiente: R$%d\n", valor,
					this.num, this.saldo);
		}
	}
}
