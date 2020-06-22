package model;

public class Palavra {
	
	/*
	 * 	Objeto que servirá para armazenar, em RAM, e manipular a palavra escolhida.
	 */
	
	String   silabasConcatenadas; // Armazena as silabas da palavra concatenadas, ou seja, a palavra.
	String[] silabasPalavra;    // Armazenará, em ordem, as silabas que constituem as palavras.
	int numSilabas;   		    // Armazenará o número de sílabas.
	int numLetras;				// Armazena o número de letras na Palavra.
	String[] silabasAdicionais; // Armazenará as sílabas que não farão parte da palara.
	
	public Palavra (char[] linha, int silabas)
	{
		
		/*
		 *   	Método para inicializar o objeto, o vetor "linha" corresponde a uma linha do arquivo.
		 */
		
		// Variável qeu armazena a soma do numero de sílabas, constituintes ou não, da palavra.
		int numTotalSilabas;
		
				
		if(linha != null && linha[0] != '.')
		{
			if(silabas >= 5) numTotalSilabas = 8;
			else numTotalSilabas = 6;
			
			numSilabas = silabas;
			silabasConcatenadas = "";
			silabasPalavra    = new String[numSilabas];
			
			if(silabas >= 8) silabasAdicionais = null;
			else silabasAdicionais = new String[numTotalSilabas - numSilabas];
			
			int contador = 0;
			int inicioSilaba; // Servira para armazenar em qual índice da palvra a sílaba começará
			
			// O laço abaixo percorerrá o Vetor para armazenar as Sílabas que constituem a palavra e concatená-las.
			for(int i = 0; i < numSilabas; i++)
			{
				inicioSilaba = contador;
				while(linha[contador] != '-' && linha[contador] != ' ')				
					contador++;
				
				silabasPalavra[i]    = new String(linha, inicioSilaba, contador - inicioSilaba);
				silabasConcatenadas += silabasPalavra[i];
				contador++;
			}

			numLetras = silabasConcatenadas.length();
						
			// Variável utilizada para impedir overflow no vetor "linhas".
			int tamanho = linha.length;
			if(silabas < 8)
			{
				// O Laço abaixo é análogo ao anterior, porém servirá para armazenar as Sílabas que não constituem a palavra
				for(int i = 0; i < (numTotalSilabas-numSilabas); i++)
				{
					inicioSilaba = contador;
					
					while(contador != (tamanho-1) && linha[contador] != ' ')
						contador++;
					
					if(contador == tamanho-1) contador++;				
					silabasAdicionais[i] = new String(linha, inicioSilaba, contador - inicioSilaba);
					contador++;
				}
			}
		}else
		{
			silabasPalavra = null;
			silabasAdicionais = null;
			silabasConcatenadas = null;
			numSilabas = 0;
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
	
	public int getNumSilabas()
	{
		return(numSilabas);
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
		int numTotalSilabas = numSilabas + silabasAdicionais.length;
		
		retorno += "NumSilabas: " + numSilabas  + "\n";
		retorno += "NumLetras: " + numLetras + "\n";
		retorno += "Palavra: " + silabasConcatenadas + "\n";
		
		retorno+= "Silabas Extras: ";
		for(int i = 0; i <  numTotalSilabas - numSilabas; i++) retorno += silabasAdicionais[i] + " ";
		
		return retorno;
	}
	
}
