package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Gerenciamento {
	private String dadoArq;
	
	public boolean verificarAdmistrador(String nome) {
		try {
			FileReader arq = new FileReader("administradores.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			
			dadoArq = lerArq.readLine(); // lê a primeira linha do arquivo para começar o loop
			
			while (dadoArq != null && dadoArq != nome) // procura o nome no arquivo até o final
				dadoArq = lerArq.readLine();
			
			arq.close();
			
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		
		return (dadoArq == null) ? false : true;
	}
}
