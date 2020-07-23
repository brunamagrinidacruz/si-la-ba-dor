package model;

import exception.ArquivoException;

public class Palavra {
	
	/*
	 * 	Objeto que servirá para armazenar, em RAM, e manipular a palavra escolhida.
	 */
	
	String   silabasConcatenadas; // Armazena as silabas da palavra concatenadas, ou seja, a palavra.
	String[] silabasPalavra;    // Armazenará, em ordem, as silabas que constituem as palavras.
	String[] silabasAdicionais; // Armazenará as sílabas que não farão parte da palara.
	String[] silabasCompleto; //Armazena todas as silabas em ordem aleatória.
	int numSilabas;   		    // Armazenará o número de sílabas.
	int numTotalSilabas;   		// Armazenará o número de total de sílabas contando com as silabas adicionais.
	int numLetras;				// Armazena o número de letras na Palavra.
	
	public Palavra (char[] linha, int silabas)	throws ArquivoException
	{
		/*
		 *   	Método para inicializar o objeto, o vetor "linha" corresponde a uma linha do arquivo.
		 */
		
		// O trecho abaio tratará as exceções mais simples dos parâmetros deste método.
		if(linha == null)
			throw new ArquivoException("Foi passado um valor nulo como parâmetro da função.");
		// Trata o caso em que a linha fornecida é inválida.
		if(linha.length <= 2 || linha[0] == '.')
			throw new ArquivoException("A linha a seguir no arquivo é vazia, ou inválida.\n Linha: " + String.valueOf(linha));
		if(silabas <= 1 || silabas > 7)
			throw new ArquivoException("O número de sílabas fornecido é inválido, esta classe só trata palavras de 2 a 7 sílabas, valor passado: " + silabas);
		
		// Variável que armazena a soma do numero de sílabas, constituintes ou não, da palavra.
		if(silabas >= 5) numTotalSilabas = 8;
		else numTotalSilabas = 6;

		numSilabas = silabas;
		silabasConcatenadas = "";
		silabasPalavra = new String[numSilabas];
		silabasAdicionais = new String[numTotalSilabas - numSilabas];

		int contador = 0;
		int inicioSilaba; // Servira para armazenar em qual índice da palvra a sílaba começará
		
		// O laço abaixo percorerrá o Vetor para armazenar as Sílabas que constituem a palavra e concatená-las.
		for(int i = 0; i < numSilabas; i++)
		{
			inicioSilaba = contador;
			while(linha[contador] != '-' && linha[contador] != ' ')				
				contador++;
			
			// Trata os possíveis casos em que o parâmetro "silabas" foi passado incorretamente.
			if(i == numSilabas - 1 && linha[contador] == '-')
				throw new ArquivoException("O número de sílabas fornecido é menor que o número de sílabas da palavra lida no arquivo.\n Linha: " + String.valueOf(linha) + "\n NumSilabas fornecido: " + silabas);
			if(i < numSilabas - 1 && linha[contador] == ' ')
				throw new ArquivoException("O número de sílabas fornecido é maior que o número de sílabas da palavra lida no arquivo.\n Linha: " + String.valueOf(linha) + "\n NumSilabas fornecido: " + silabas);
			
			silabasPalavra[i]    = new String(linha, inicioSilaba, contador - inicioSilaba);
			silabasConcatenadas += silabasPalavra[i];
			contador++;
		}

		numLetras = silabasConcatenadas.length();
					
		// Variável utilizada para impedir overflow no vetor "linhas".
		int tamanho = linha.length;

		// O Laço abaixo é análogo ao anterior, porém servirá para armazenar as Sílabas que não constituem a palavra
		for(int i = 0; i < (numTotalSilabas-numSilabas); i++)
		{
			inicioSilaba = contador;
			
			while(contador < tamanho-1 && contador != (tamanho-1) && linha[contador] != ' ')
				contador++;
			
			if(contador == tamanho-1) contador++;
			silabasAdicionais[i] = new String(linha, inicioSilaba, contador - inicioSilaba);
			contador++;
		}
		// Caso em que não foram lidos todos caracteres de "linha"
		if(contador < linha.length - 1)
			throw new ArquivoException("A linha a seguir não respeita o padrão do arquivo: " + String.valueOf(linha));
		
		silabasCompleto = new String[numTotalSilabas];
		Random r = new Random();
		
		int indexSilabasPalavras = 0; /*!< Armazena qual último analisado da silabasPalavras */
		int indexSilabasAdicionais = 0; /*!< Armazena qual último analisado da silabasAdicionais */
		for(int i = 0; i < numTotalSilabas; i++) {
			int randomNumber = r.getIntRand(2); /*!< Retornará valores 0 ou 1 */
			
			/*!< Se todas as silabas das silabasPalavras foram utilizadas, deve pegar apenas das silabasAdicionais */
			if(indexSilabasPalavras == (silabasPalavra.length)) {
				silabasCompleto[i] = new String(silabasAdicionais[indexSilabasAdicionais]);
				indexSilabasAdicionais++;
				continue;
			}

			/*!< Se todas as silabas das silabasAdicionais foram utilizadas, deve pegar apenas das silabasPalavras */
			if(indexSilabasAdicionais == (silabasAdicionais.length)) {
				silabasCompleto[i] = new String(silabasPalavra[indexSilabasPalavras]);
				indexSilabasPalavras++;
				continue;
			}
			
			/*!< Se nenhum dos dois conjuntos de palavras teve todas as palavras selecionadas, pegar aleatoriamente entre os dois */
			if(randomNumber % 2 == 0) {
				silabasCompleto[i] = new String(silabasPalavra[indexSilabasPalavras]);
				indexSilabasPalavras++;
			} else {
				silabasCompleto[i] = new String(silabasAdicionais[indexSilabasAdicionais]);
				indexSilabasAdicionais++;
			}
		}
	}
	
	public String getPalavra()
	{
		return(silabasConcatenadas);
	}
	
	public String[] getSilabasPalavra()
	{
		return(silabasPalavra);
	}
	
	public String[] getSilabasAdicionais()
	{
		return(silabasAdicionais);
	}
	
	public String[] getSilabasCompleto()
	{
		return(silabasCompleto);
	}
	
	
	public int getNumSilabas()
	{
		return(numSilabas);
	}	
	
	public int getNumTotalSilabas() {
		return numTotalSilabas;
	}
	
	public int getNumLetras()
	{
		return(numLetras);
	}
	
	@Override
	public String toString()
	{
		/*
		 * 		ÚTIL PARA DEBUG, por enquanto não terá utilidade no trabalho.
		 *  Antes desta chamada, deve-se garantir de que a Palavra não é nula.
		 */
		
		String retorno = "";
	
		retorno += "NumSilabas: " + numSilabas  + "\n";
		retorno += "NumTotalSilabas: " + numTotalSilabas + "\n";
		retorno += "NumLetras: " + numLetras + "\n";
		retorno += "Palavra: " + silabasConcatenadas + "\n";
		
		retorno+= "Silabas Extras: ";
		for(int i = 0; i <  numTotalSilabas - numSilabas; i++) retorno += silabasAdicionais[i] + " ";
		
		/*retorno+= "\n";
		retorno += "Todas as silabas: ";
		for(int i = 0; i < numTotalSilabas; i++) retorno += silabasCompleto[i] + " ";		
		*/
		return retorno;
	}
	
}
