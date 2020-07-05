package model;

public class Jogador extends Usuario {
	private int pontuacao;
	
	public Jogador(String nome, int tipoUsuario) {
		super(nome, tipoUsuario);
	}
	
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public int getPontuacao() {
		return this.pontuacao;
	}
}
