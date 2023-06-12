package livraria.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Estante {
	private List<Livro> livros = new ArrayList<>();
	
	public Estante(){}

	public Estante(List<Livro> livros) {
		this.livros = livros;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public abstract void adicionarLivro (String nome);
	public abstract void retirarLivro(int matricula, int cod);
	public abstract void devolverLivro(int cod);
	public abstract void encontrarLivro(int cod);
	public abstract void listarLivros();
	
}
