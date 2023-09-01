package Lottery_Draw;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	//arrays que armazenam as informações
	private static int[] ticketsCadastrados = new int[5];
	private static double[] valoresTickets = new double[5];
	private static double[] valorDoPremio = new double[5];

	//esse método é o ponto de entrada, ele chama o método inicial() que exibe o Menu que processa a escolha do usuário
	//depois chama o método keyPress() que guarda a entrada do usuário antes de retornar ao menu novamente.
	public static void main(String[] args) {
			inicial();
			keyPress();			
	}
			
	//exibe o menu de opções para o usuário, lê e guarda o input
	private static void inicial() {
		
		Scanner leia = new Scanner (System.in);	
		int codMenu;
		
		System.out.println("\n******* Loteria *******");
		System.out.println("    Faça aqui a sua aposta \n");
		System.out.println("---------- Menu ----------\n");
		System.out.println("1 - Cadastrar tickets");
		System.out.println("2 - Visualizar os tickets cadastrados");
		System.out.println("3 - Visualizar resultado do sorteio");
		System.out.println("4 - Sair do sistema\n");
		System.out.println("--------------------------\n");
		
		System.out.println("Digite a opção escolhida: ");
		codMenu = leia.nextInt();
		leia.nextLine();
		
		switch(codMenu) {
		
		//cadastro dos tíckets com números de 1 a 10 a escolha do usuário
		case 1:
			System.out.println("\nCadastre 5 números de 1 a 10:");
			for (int x = 0; x < 5; x++) {
				System.out.println("\nTicket cadastrado n. " + (x+1) + ": ");
				ticketsCadastrados[x] = leia.nextInt();
				valoresTickets[x] = 5.00; //R$5,00 para cada ticket cadastrado 
			}
			leia.nextLine();
			keyPress();	
			break;
			
		//visualização dos tíckets cadastrados e cálculo do valor total
		case 2:
			System.out.println("Os tickets cadastrados são: \n");
			double valorTotal = 0.0;
			
            for (int i = 0; i < ticketsCadastrados.length; i++) {
                if (ticketsCadastrados[i] != 0) {
                    System.out.println("Ticket nº " + ticketsCadastrados[i] + " - Valor: R$ " + valoresTickets[i]);
                    valorTotal += valoresTickets[i]; 
                }
            }
            System.out.println("\n*** Valor total dos tickets: R$ " + valorTotal + "***");
            keyPress();    
            break;
            
        //aqui ele simula um sorteio e determina os tickets vencedores
		case 3:
			visualizarResultado();
								
			keyPress();
			break;
			
		//encerra o programa
		case 4:
			System.out.println("\n*** Loteria agradece a sua participação! ***");
			System.exit(0); 
			break;
		
		//mensagem para o caso o numero digitado escolhido pelo usuario não seja nenhum da lista do menu
		default:
			System.out.println("Código inválido.");
			leia.nextLine();
			break;
		}
		
		leia.close();
	}
	
	//aqui ele simula o sorteio com números aleatório de 01 a 10
	private static void visualizarResultado() {
		//usa a classe Random para garantir que não haja numero duplicado
		Random random = new Random();
		HashSet<Integer> numerosSorteados = new HashSet<>();
		
		//este loop gera números aleatórios até que o conj 5 se complete
		while (numerosSorteados.size() < 5) {
			int numeroSorteado = random.nextInt(10) + 1;
			numerosSorteados.add(numeroSorteado);
		}
		
		//os numerosSorteados recebem os numeros aleatórios
		System.out.println("\nOs números sorteados são: ");
		for (int numero : numerosSorteados) {
			System.out.println(numero);
		}
		
		//aqui os ticketsCadastrados são comparados com o numero aleatório
		System.out.println("\nOs tickets vencedores são:\n");
		double totalPremio = 0.0;
		
        for (int i = 0; i < ticketsCadastrados.length; i++) {
            for (int numero : numerosSorteados) {
                if (ticketsCadastrados[i] == numero) {
                	
                	valorDoPremio[i] = valoresTickets[i] * 0.85; //15%
                	
                	totalPremio += valorDoPremio[i]; 
                	
                    System.out.println("Ticket nº " + ticketsCadastrados[i] + " - Valor do prêmio: R$ " + valorDoPremio[i]);
                }
            }
        }
        System.out.println("\n*** Valor total de prêmio a receber: R$ " + totalPremio + "***");
        
	}
	
	//aqui o programa aguarda o usuário pressionar Enter para voltar ao Menu
	private static void keyPress() {

		System.out.println("\n\nPressione ENTER para voltar ao menu.");

		//o System.in.read espera a entrada do usuário
		try {
			System.in.read();

		} catch (IOException e) {
		}
		
		inicial();
	}
	
}
