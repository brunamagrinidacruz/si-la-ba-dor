package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.OperacoesException;

public class OperacoesArquivo {
	private String dadoArq;
	
	public boolean verificarAdmistrador(String nome) throws OperacoesException {
		try {
			if (nome.equals("")) throw new OperacoesException("Nome não preenchido.");
			
			FileReader arq = new FileReader("administradores.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			
			dadoArq = lerArq.readLine(); // lï¿½ a primeira linha do arquivo para comeï¿½ar o loop
			
			while (dadoArq != null && !dadoArq.equals(nome)) // procura o nome no arquivo atï¿½ o final
				dadoArq = lerArq.readLine();
			
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		
		return (dadoArq == null) ? false : true;
	}
}
