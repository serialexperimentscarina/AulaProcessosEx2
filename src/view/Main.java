package view;

import java.util.Scanner;

import controller.KillController;

public class Main {

	public static void main(String[] args) {
		int opc;
		String processo;
		KillController killController = new KillController();
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println("\nMenu : \n1 - Liste processos \n2- Matar um processo \n3 - Finalizar");
			opc = input.nextInt();
			switch (opc) {
				case 1:
					killController.listaProcessos();
					break;
				case 2:
					System.out.println("Digite o nome ou PID do processo que deseja encerrar: ");
					input.nextLine();
					processo = input.nextLine();
					killController.mataProcesso(processo);
					break;
				case 3: 
					System.out.println("Encerrando execução.");
					break;
				default: 
					System.out.println("Opção inválida!");
					break;
			}
		} while (opc != 3);
		input.close();
		
	}

}
