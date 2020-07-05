package model;

public class Usuario {
	private String nome;
	private int tipoUsuario;
	
	public Usuario(String nome, int tipoUsuario) {
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
