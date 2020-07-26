package model;

import exception.ArquivoException;

public class Palavra {
	
	/*
	 * 	Objeto que servir� para armazenar, em RAM, e manipular a palavra escolhida.
	 */
	
	String   silabasConcatenadas; 	// Armazena as s�labas da palavra concatenadas, ou seja, a palavra.
	String[] silabasPalavra;    	// Armazenar�, em ordem, as silabas que constituem as palavras.
	String[] silabasAdicionais;		// Armazenar� as s�labas que n�o far�o parte da palara.
	String[] silabasCompleto; 		//Armazena todas as silabas em ordem aleat�ria.
	int numSilabas;   		    	// Armazenar� o n�mero de s�labas.
	int numTotalSilabas;   			// Armazenar� o n�mero de total de s�labas contando com as silabas adicionais.
	int numLetras;					// Armazena o n�mero de letras na Palavra.
	
	public Palavra (char[] linha, int silabas)	throws ArquivoException
	{
		/*
		 *   	M�todo para inicializar o objeto, o vetor "linha" corresponde a uma linha do arquivo.
		 */
		
		// O trecho abaio tratar� as exce��es mais simples dos par�metros deste m�todo.
		if(linha == null)
			throw new ArquivoException("Foi passado um valor nulo como parametro da fun��o.");
		// Trata o caso em que a linha fornecida � inv�lida.
		if(linha.length <= 2 || linha[0] == '.')
			throw new ArquivoException("A linha a seguir no arquivo � vazia, ou inv�lida.\n Linha: " + String.valueOf(linha));
		if(silabas <= 1 || silabas > 7)
			throw new ArquivoException("O n�mero de s�labas fornecido � inv�lido, esta classe s� trata palavras de 2 a 7 s�labas, valor passado: " + silabas);
		
		// Vari�vel que armazena a soma do n�mero de s�labas, constituintes ou n�o, da palavra.
		if(silabas >= 5) numTotalSilabas = 8;
		else numTotalSilabas = 6;

		numSilabas = silabas;
		silabasConcatenadas = "";
		silabasPalavra = new String[numSilabas];
		silabasAdicionais = new String[numTotalSilabas - numSilabas];

		int contador = 0;
		int inicioSilaba; // Servir� para armazenar em qual �ndice da palvra a s�laba come�ar�
		
		// O la�o abaixo percorerr� o Vetor para armazenar as S�labas que constituem a palavra e concaten�-las.
		for(int i = 0; i < numSilabas; i++)
		{
			inicioSilaba = contador;
			while(linha[contador] != '-' && linha[contador] != ' ')				
				contador++;
			
			// Trata os poss�veis casos em que o par�metro "s�labas" foi passado incorretamente.
			if(i == numSilabas - 1 && linha[contador] == '-')
				throw new ArquivoException("O n�mero de s�labas fornecido � menor que o n�mero de s�labas da palavra lida no arquivo.\n Linha: " + String.valueOf(linha) + "\n NumSilabas fornecido: " + silabas);
			if(i < numSilabas - 1 && linha[contador] == ' ')
				throw new ArquivoException("O n�mero de s�labas fornecido � maior que o n�mero de s�labas da palavra lida no arquivo.\n Linha: " + String.valueOf(linha) + "\n NumSilabas fornecido: " + silabas);
			
			silabasPalavra[i]    = new String(linha, inicioSilaba, contador - inicioSilaba);
			silabasConcatenadas += silabasPalavra[i];
			contador++;
		}

		numLetras = silabasConcatenadas.length();
					
		// Vari�vel utilizada para impedir overflow no vetor "linhas".
		int tamanho = linha.length;

		// O La�o abaixo � an�logo ao anterior, por�m servir� para armazenar as S�labas que n�o constituem a palavra
		for(int i = 0; i < (numTotalSilabas-numSilabas); i++)
		{
			inicioSilaba = contador;
			
			while(contador < tamanho-1 && contador != (tamanho-1) && linha[contador] != ' ')
				contador++;
			
			if(contador == tamanho-1) contador++;
			silabasAdicionais[i] = new String(linha, inicioSilaba, contador - inicioSilaba);
			contador++;
		}
		// Caso em que n�o foram lidos todos caracteres de "linha"
		if(contador < linha.length - 1)
			throw new ArquivoException("A linha a seguir n�o respeita o padr�o do arquivo: " + String.valueOf(linha));
		
		silabasCompleto = new String[numTotalSilabas];
		Random r = new Random();
		
		int indexSilabasPalavras = 0; /*!< Armazena qual �ltimo analisado da silabasPalavras */
		int indexSilabasAdicionais = 0; /*!< Armazena qual �ltimo analisado da silabasAdicionais */
		for(int i = 0; i < numTotalSilabas; i++) {
			int randomNumber = r.getIntRand(2); /*!< Retornar� valores 0 ou 1 */
			
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
		 * 		�TIL PARA DEBUG, por enquanto n�o ter� utilidade no trabalho.
		 *  Antes desta chamada, deve-se garantir de que a Palavra n�o � nula.
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
