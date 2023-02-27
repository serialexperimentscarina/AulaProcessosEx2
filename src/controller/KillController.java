package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {
	
	public KillController() {
		super();
	}
	
	// Identifica e retorna o nome do Sistema Operacional
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	// Verifica o SO e, de acordo com SO, seleciona o comando para listar os processos ativos.
	public void listaProcessos() {
		String process = (os().contains("Windows")) ? "tasklist /fo table" : "ps -ef";
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Recebe um PID como parâmetro de entrada e, de acordo com SO, seleciona o comando para matar o processo.
	public void mataPid(int pid) {
		StringBuffer process = new StringBuffer();
		process.append((os().contains("Windows")) ? "taskkill /pid " : "kill -9 ");
		process.append(pid);
		try {
			Runtime.getRuntime().exec(process.toString());
			System.out.println("Processo encerrado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Recebe um nome como parâmetro de entrada e, de acordo com SO, seleciona o comando para matar o processo.
	public void mataNome(String nome) {
		StringBuffer process = new StringBuffer();
		process.append((os().contains("Windows")) ? "taskkill /im " : "pkill -f ");
		process.append(nome);
		try {
			Runtime.getRuntime().exec(process.toString());
			System.out.println("Processo encerrado!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Mata um processo passado pelo usuário
	public void mataProcesso(String processo) {
		try {
			int pid = Integer.parseInt(processo);
			mataPid(pid);
		} catch (NumberFormatException e) {
			mataNome(processo);
		}
	}

}
