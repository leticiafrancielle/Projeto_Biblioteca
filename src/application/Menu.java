package application;

import livraria.controller.Bibliotecario;
import livraria.model.Biblioteca;
import livraria.model.Livro;
import livraria.util.Cores;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Livro> livros = new ArrayList<>();
		int opcao = 1;
		livros.add(new Livro("Dom quixote", 1));
		livros.add(new Livro("Diario de um banana", 2));
		livros.add(new Livro("O alquimista", 3));
		Biblioteca b1 = new Biblioteca(livros);

		System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "BEM VINDO À BIBLIOTECA FUTURISTA!" + Cores.TEXT_RESET);
		System.out.print(Cores.TEXT_WHITE_BOLD_BRIGHT + "Informe seu usuário: ");
		String usuario = sc.nextLine();
		System.out.print("Informe sua senha: " + Cores.TEXT_RESET);
		int senha = sc.nextInt();
		Bibliotecario user = new Bibliotecario(usuario, senha);
		b1 = new Biblioteca(livros, user);

		while (user.verificarSenha(usuario, senha) == false) {
			System.out.print(Cores.TEXT_WHITE_BOLD_BRIGHT + "Informe seu usuário: ");
			sc.nextLine();
			usuario = sc.nextLine();
			System.out.print("Informe sua senha: " + Cores.TEXT_RESET);
			senha = sc.nextInt();
		}
		while (true) {
			System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "\n*****************************************"
					+ "\n*                 MENU:                 *" + "\n* 1 - Cadastrar alune                   *"
					+ "\n* 2 - Visualizar cadastros dos alunes   *" + "\n* 3 - Remover alune                     *"
					+ "\n* 4 - Adicionar livro                   *" + "\n* 5 - Retirar um livro pelo código      *"
					+ "\n* 6 - Devolver um livro                 *" + "\n* 7 - Encontrar um livro pelo código    *"
					+ "\n* 8 - Listar livros disponíveis         *" + "\n* 9 - Sair                              *"
					+ "\n*****************************************" + Cores.TEXT_RESET);
			System.out.print("\nEntre com a opção desejada: ");
			try {
				opcao = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Digite valores inteiros! ");
				sc.nextLine();
				opcao = 0;
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Cadastrar alune: \n" + Cores.TEXT_RESET);
				sc.nextLine();
				System.out.print("Informe o nome do alune: ");
				String nome = sc.nextLine();
				user.cadastrarAluno(nome);
				user.keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Visualizar cadastro alunes: \n" + Cores.TEXT_RESET);
				user.listarCadastros();
				user.keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Remover alune: \n" + Cores.TEXT_RESET);
				System.out.print("Entre com a matricula do alune que deseja retirar: ");
				int matricula = sc.nextInt();
				user.removerAluno(matricula);
				user.keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Adicionar livro \n" + Cores.TEXT_RESET);
				sc.nextLine();
				System.out.print("Informe o nome do livro: ");
				String nomeLivro = sc.nextLine();
				b1.adicionarLivro(nomeLivro);
				user.keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Retirar livro: \n" + Cores.TEXT_RESET);
				System.out.print("Entre com o código do livro que deseja retirar: ");
				int cod = sc.nextInt();
				System.out.println("Digite a matrícula do alune que deseja retirar o livro:");
				matricula = sc.nextInt();
				b1.retirarLivro(cod, matricula);
				user.keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Devolver livro: \n" + Cores.TEXT_RESET);
				System.out.print("Entre com o código do livro que deseja devolver: ");
				cod = sc.nextInt();
				b1.devolverLivro(cod);
				user.keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Encontrar livro: \n" + Cores.TEXT_RESET);
				System.out.print("Informe o código do livro: ");
				cod = sc.nextInt();
				b1.encontrarLivro(cod);
				user.keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "Listar livros: \n" + Cores.TEXT_RESET);
				b1.listarLivros();
				user.keyPress();
				break;
			case 9:
				encerrar();
				sc.close();
				System.exit(0);
			default:
				System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "\nOpção inválida!" + Cores.TEXT_RESET);
				user.keyPress();
				break;
			}
			b1.aguardar();
		}
	}

	public static void encerrar() {
		System.out.println("Encerrando programa...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Erro ao encerrar programa!");
			return;
		}
		System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "\nPrograma encerrado!" + Cores.TEXT_RESET);
	}
}