package view;

import control.ManipulaArquivo;
import control.Niveis;
import exception.ArquivoException;

public class Testes {
	
	public static void main(String[] args) throws ArquivoException {
		ManipulaArquivo manipulaArquivo = new ManipulaArquivo(Niveis.NIVEL1);
		System.out.println(manipulaArquivo.recebePalavra());
	}

}


