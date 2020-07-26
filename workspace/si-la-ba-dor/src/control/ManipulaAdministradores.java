package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.OperacoesException;

public class ManipulaAdministradores {
	private String dadoArq;
	
	public boolean verificarAdmistrador(String nome) throws OperacoesException {
		try {
			if (nome.equals("")) throw new OperacoesException("Nome não preenchido.");
			
			FileReader arq = new FileReader("administradores.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			
			dadoArq = lerArq.readLine(); // lê a primeira linha do arquivo para comecar o loop
			
			while (dadoArq != null && !dadoArq.equals(nome)) // procura o nome no arquivo até o final
				dadoArq = lerArq.readLine();
			
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		
		return (dadoArq == null) ? false : true;
	}
}
