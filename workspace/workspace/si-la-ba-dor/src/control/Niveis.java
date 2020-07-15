package control;

public enum Niveis {
	NIVEL1(1), NIVEL2(2), NIVEL3(3);
	
	private int nivel;
	
	Niveis(int nivel) {
		this.nivel = nivel;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	
}
