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

}
