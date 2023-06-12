package livraria.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import livraria.controller.Bibliotecario;
import livraria.repository.Cadastro;
import livraria.util.Cores;

public class Biblioteca extends Estante {

	private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private List<Livro> livrosRetirados = new ArrayList<>();
	int numero = 3;
	private Bibliotecario bibliotecario;

	public Biblioteca() {
	}

	public Biblioteca(List<Livro> livros) {
		super(livros);
	}

	public Biblioteca(List<Livro> livros, Bibliotecario bibliotecario) {
		super(livros);
		this.bibliotecario = bibliotecario;
	}

	public List<Livro> getLivros() {
		return super.getLivros();
	}

	public Bibliotecario getBibliotecario() {
		return bibliotecario;
	}

	public void setBibliotecario(Bibliotecario bibliotecario) {
		this.bibliotecario = bibliotecario;
	}

	@Override
	public void adicionarLivro(String nome) {
		Livro livro = new Livro(nome, gerarNumero());

		super.getLivros().add(livro);
		System.out
				.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "Livro " + livro.getNome() + " adicionado!" + Cores.TEXT_RESET);
		System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "Código " + livro.getCodigo() + Cores.TEXT_RESET);
	}

	@Override
	public void retirarLivro(int cod, int matricula) {
		Livro livroRetirado = null;
		boolean foiDevolvido = false;

		for (Livro livro : super.getLivros()) {
			if (livro.getCodigo() == cod) {
				livroRetirado = livro;
			}
		}

		for (Livro livro : livrosRetirados) {
			if (livro.getCodigo() == cod) {
				foiDevolvido = true;
				livroRetirado = livro;
			}
		}

		if (livroRetirado != null && foiDevolvido == false && bibliotecario.encontrarAluno(matricula) == true) {

			super.getLivros().remove(livroRetirado);
			livrosRetirados.add(livroRetirado);
			System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "O livro " + livroRetirado.getNome()
					+ " foi retirado com sucesso! " + Cores.TEXT_RESET);
			LocalDate dataDevolucao = LocalDate.now().plusWeeks(1);
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "AVISO! Data de devolução: até " + fmt.format(dataDevolucao)
					+ Cores.TEXT_RESET);
		} else if (foiDevolvido == true) {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "O livro " + livroRetirado.getNome() + " já foi retirado!"
					+ Cores.TEXT_RESET);
		} else if (bibliotecario.encontrarAluno(matricula) == false) {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "Não existe aluno com esta matrícula" + Cores.TEXT_RESET);
		} else {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "Nenhum livro com o código " + cod + " foi encontrado!"
					+ Cores.TEXT_RESET);
		}
	}

	@Override
	public void devolverLivro(int cod) {
		Livro livroDevolvido = null;
		for (Livro livro : livrosRetirados) {
			if (livro.getCodigo() == cod) {
				livroDevolvido = livro;
			}
		}

		if (livroDevolvido != null) {
			super.getLivros().add(livroDevolvido);
			livrosRetirados.remove(livroDevolvido);
			System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "O livro: " + livroDevolvido.getNome() + " foi devolvido!"
					+ Cores.TEXT_RESET);
		} else {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "Não é possível devolver um livro que não foi retirado!"
					+ Cores.TEXT_RESET);
		}
	}

	@Override
	public void encontrarLivro(int cod) {
		Livro livroEncontrado = null;
		boolean foiDevolvido = false;

		for (Livro livro : super.getLivros()) {
			if (livro.getCodigo() == cod) {
				livroEncontrado = livro;
			}
		}

		for (Livro livro : livrosRetirados) {
			if (livro.getCodigo() == cod) {
				foiDevolvido = true;
				livroEncontrado = livro;
			}
		}
		if (livroEncontrado != null && foiDevolvido == false) {
			System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "Livro encontrado, nome: " + livroEncontrado.getNome()
					+ ", código: " + livroEncontrado.getCodigo() + Cores.TEXT_RESET);
		} else if (foiDevolvido == true) {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "O livro " + livroEncontrado.getNome() + " já foi retirado!"
					+ Cores.TEXT_RESET);
		} else {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "Não foi encontrado nenhum livro com o código: " + cod
					+ Cores.TEXT_RESET);
		}
	}

	@Override
	public void listarLivros() {
		if (super.getLivros().isEmpty()) {
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "Nenhum livro disponível!" + Cores.TEXT_RESET);
		} else {
			System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "\nLista de livros: " + Cores.TEXT_RESET);
			for (Livro book : super.getLivros()) {
				System.out.println("° " + book.getNome() + ", código: " + book.getCodigo());
			}
		}
	}

	public int gerarNumero() {
		return ++numero;
	}

	public void aguardar() {

		System.out.println("Aguarde...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println(Cores.TEXT_RED_BOLD_BRIGHT + "Erro inesperado!");
			return;
		}
	}
}
