package view;

import control.ManipulaArquivo;
import control.Niveis;

public class Testes {
	
	public static void main(String[] args) {
		ManipulaArquivo manipulaArquivo = new ManipulaArquivo(Niveis.NIVEL1);
		System.out.println(manipulaArquivo.recebePalavra());
	}

}


