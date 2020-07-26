package control;
import java.io.*;

import model.*; 

import java.util.LinkedList;
import java.util.Queue;

import exception.ArquivoException;  

public class ManipulaSilabas {
		
	static int indices[];   // Armazena os �ndices de busca, referente a cada n�mero de s�labas.
	static int numPalavras; // Armazena o numero de palavras salvas no arquivo.
	static Niveis dificuldade;
	static String nomeArquivo;
	// Fila para armazenar as �ltimas quatro palavras escolhidas, utilizada para impedir repeti��es.
	private Queue<Integer> cache = new LinkedList<Integer>(); 
	private int tamanhoCabecalho;
	private Random r;
	
	private String dado;
	
	public ManipulaSilabas(Niveis nivel) throws ArquivoException
	{
		File dados;
		dificuldade = nivel;
		r = new Random();
		
		/*
		 * 		A condicional abaixo verifica qual n�vel foi escolhido para selecionar o arquivo selecionado
		 * 	e instanciar o vetor "indices", visto que, como cada n�vel possui um valor diferente de n�meros de
		 *  s�labas selecionados, seu tamanho ser� diferente para cada arquivo.
		 */
		switch(nivel) 
		{
			case NIVEL1:
				nomeArquivo = "nivel1.txt";
				indices = new int[2];
				tamanhoCabecalho = 3;
				break;
			case NIVEL2:
				nomeArquivo = "nivel2.txt";
				indices = new int[3];
				tamanhoCabecalho = 4;
				break;
			case NIVEL3:
				nomeArquivo = "nivel3.txt";
				indices = new int[3];
				tamanhoCabecalho = 4;
				break;
			default:
				throw new ArquivoException("A dificuldade selecionada é inválida.");
		}
		
		dados = new File(nomeArquivo);
		
		try(FileReader fr = new FileReader(dados);
			BufferedReader br = new BufferedReader(fr)){
			// L� e armazena o cabe�alho do arquivo.
			numPalavras = Integer.parseInt(br.readLine());
			
			int tamanho = indices.length;
			for(int i = 0; i < tamanho; i++)
				indices[i] = Integer.parseInt(br.readLine());
			
		} catch(IOException e) 
		{
			e.fillInStackTrace();
		}
	}
	
	private void atualizaCache(int linha)
	{
		/*
		 * M�todo para inserir e remover na fila, mantendo sempre quatro ou menos elementos.
		 */
		if(cache.size() == 4)
		{
			cache.remove();
		}
		
		cache.add(linha);
	}
	
	public Palavra recebePalavra()
	{
		/*
		 * 		M�todo que seleciona um n�mero aleat�rio de silabas com base no n�vel e 
		 *  percorre o arquivo, e escolhe uma palavra aleat�ria com a quantidade de s�labas.
		 */
		
		int silabas = 0;
		int numSilabas = 0; // Armazena o n�mero de s�labas da palavra.
		int contador = 0;   // Contador, que ao atingir um determinado valor, interrompe a excecu��o do algoritmo do cache pois caso, num arquivo, um determinado n�mero de s�labas seja menor que o tamanho do cache, o programa entrar� em loop infinito.
		
		switch(dificuldade) 
		{
			case NIVEL1:
				/*!< getIndRand ir� retornar valores entre [0, 1] ent�o ao somar 2, os valores ir�o variar entre [2, 3] */
				silabas = r.getIntRand(2);
				numSilabas = silabas + 2;
				break;
			case NIVEL2:
				/*!< getIndRand ir� retornar valores entre [0, 2] ent�o ao somar 2, os valores ir�o variar entre [2, 4] */
				silabas = r.getIntRand(3);
				numSilabas = silabas + 2;
				break;
			case NIVEL3:
				/*!< getIndRand ir� retornar valores entre [0, 2] ent�o ao somar 5, os valores ir�o variar entre [5, 7] */
				silabas = r.getIntRand(3);
				numSilabas = silabas + 5;
				break;
			default:
				silabas = 0;
				break;
		}
		
		if(nomeArquivo == null) return null;
		
		Palavra nova = null;     // Contem o objeto "palavra" a ser retornado.
		File dados = new File(nomeArquivo);
		int palavraEscolhida; 	 // Vari�vel que armazenar� quantas linhas dever�o ser puladas para chegar a palavra escolhida aleatoriamente.

		if(numSilabas < 2 || numSilabas > 7) return null;
		
		try(FileReader fr = new FileReader(dados);
			BufferedReader br = new BufferedReader(fr)){
			
			// Pula o cabe�alho:
			for(int i = 0; i < tamanhoCabecalho; i++)
				br.readLine();
			// Agora devemos, ou n�o, pular as linhas no arquivo para chegar no numero de silabas solicitado.
			for(int i = 0; i < indices[silabas]; i++)
				br.readLine();
			// Atribui, a vari�vel palavraEscolhida, o n�mero de palavras com a quantidade e s�labas escolhida
			if(silabas == indices.length - 1) palavraEscolhida = (numPalavras -indices[silabas]);
			else palavraEscolhida = (indices[silabas+1] - indices[silabas]);
			
			// Define, aleatoriamente, quantas linhas dever�o ser puladas para escolher uma palavra.
			do {
				palavraEscolhida = r.getIntRand(palavraEscolhida);
				contador++;
			} while(cache.contains(palavraEscolhida) == true && contador < 5);

			// Insere na Cache a palavra escolhida.
			atualizaCache(palavraEscolhida);
			
			// Pula a quantidade definida de linhas
			for(int i = 0; i < palavraEscolhida; i++)
				br.readLine();
			
			dado = br.readLine();
			br.close();
			// Instancia o objeto a ser retornado.
			nova = new Palavra(dado.toCharArray(), numSilabas);
			
		} catch(ArquivoException e) {
			e.getMessage();
			e.fillInStackTrace();
		} catch(IOException e) {
			e.fillInStackTrace();
		}	
		return nova;
	}

	public boolean inserePalavra(String[] silabas, String[] extras) throws ArquivoException
	{
		/*
		 *  	M��todo para inserir uma palavra num arquivo, nota-se que a inser��o ser� feita no
		 *  arquivo que foi escolhido na instancia��o da classe e est� armazenado no atributo "dificuldade".
		 *  	Como queremos manter a ordena��o de s�labas, e para evitar sobrescri��o de dados, a inser��o
		 *  ser� implementada de forma que, ser� copiado um arquivo tempor�rio "tmp.txt", no qual copiaremos o
		 *  arquivo original. Caso a inser��o seja realizada corretamente, o arquivo original ser� apagado e
		 *  o arquivo tempor�rio ser� renomeado para ser usado como o original. Caso a palavra a ser inserida
		 *  seja encontrada durante a inser��o, apagaremos o arquivo tempor�rio e o arquivo original estar�
		 *  sem modifica��es.
		 *  
		 *  	OBS: O arquivo � percorrido previamente para impedir a inser��o de uma palavra repetida.
		 *  
		 *  String[] silabas - Vetor que cont�m as s�labas constituintes da palavra a ser inserida.
		 *  String[] extras  - Vetor que cont�m as s�labas a serem embaralhadas juntos com a nova palavra.
		 */
		
		File dados = new File(nomeArquivo);
		File novo  = new File("tmp.txt");
		int numSilabasPalavra;	// Armazena o n�mero de s�labas da palavra a ser inserida e, posteriormente, assume uma posi��o referente ao vetor "indices".
		int numPalavras;		// Armazena o n�mero total de palavras no arquivo.
		int aux; 				// Vari�vel auxiliar para armazenar algum valor a ser alterado e reinserido no arquivo.
		int numLinhasPuladas;   // Armazena o n�mero de linhas a serem puladas at� alcan�ar a posi��o em que a palavra deve ser inserida.
		char linha[];		    // Conjunto de caracteres que armazena uma linha do arquivo original.
		int inicioSilaba; 	    // Varipavel que armazena a posi��o da primeira letra de uma determinada s�laba no vetor "linhas".
		int contador;	  	    // Contador utilizado para percorrer o vetor "linhas".
		boolean silabasIguais;  // Vari�vel booleana que ajuda a verificar se a palavra a ser inserida e uma dada palavra do arquivo original s�o iguais.
		int numTotalSilabas = 0;// Vari�vel que armazena o n�mero total de s�labas (constituintes e extras), devem ter sido fornecidas.
		int posicaoIndice = 0;
		String silabaLida;
		numSilabasPalavra = silabas.length;
		
		/*!< Verifica se os par�metros foram passados corretamente.*/
		switch(dificuldade)
		{
			case NIVEL1:
				numTotalSilabas = 6;
				if(numSilabasPalavra < 2 || numSilabasPalavra > 3 || numSilabasPalavra + extras.length != numTotalSilabas)
					throw new ArquivoException("O número de sílabas fornecido é inválido.");
				posicaoIndice = numSilabasPalavra - 2;
				break;
			case NIVEL2:
				numTotalSilabas = 6;
				if(numSilabasPalavra < 2 || numSilabasPalavra > 4 || numSilabasPalavra + extras.length != numTotalSilabas)
					throw new ArquivoException("O número de sílabas fornecido é inválido.");
				posicaoIndice = numSilabasPalavra -2;
				break;
			case NIVEL3:
				numTotalSilabas = 8;
				if(numSilabasPalavra < 5 || numSilabasPalavra > 7 || numSilabasPalavra + extras.length != numTotalSilabas)
					throw new ArquivoException("O número de sílabas fornecido é inválido.");
				posicaoIndice = numSilabasPalavra - 5;
				break;	
		}
		/*!<Agora deve-se garantir que a palavra ainda n�o foi inserida no arquivo.*/
		try(FileReader fr = new FileReader(dados);
				FileWriter fw = new FileWriter(novo);
				BufferedReader br = new BufferedReader(fr);
				BufferedWriter bw = new BufferedWriter(fw)) {
			
			/*!<Atualiza o cabe�alho do arquivo.*/
			numPalavras = Integer.parseInt(br.readLine());
			numPalavras++;bw.write(String.valueOf(numPalavras));
			bw.newLine();
			
			for(int i = 0; i < indices.length; i++)
			{
				aux = Integer.parseInt(br.readLine());
				if(posicaoIndice < i)
					aux++;
				indices[i] = aux;
				bw.write(String.valueOf(aux)); bw.newLine();
			}
			/*!< Pula o n�mero de linhas definidas.*/
			numLinhasPuladas = indices[posicaoIndice];
			for(int i = 0; i < numLinhasPuladas; i++)
			{
				bw.write(br.readLine());
				bw.newLine();
			}
			
			if(posicaoIndice == indices.length - 1)
				numLinhasPuladas = numPalavras - indices[posicaoIndice] - 1;
			else
				numLinhasPuladas = indices[posicaoIndice + 1] - 1 - indices[posicaoIndice];

			/*!<Realiza uma busca nas palavras com o mesmo n�mero de s�labas que a palavra a ser inserida, caso esta palavra seja encontrada, a inser��o � interrompida.*/
			for(int i = 0; i < numLinhasPuladas; i++)
			{
				linha = br.readLine().toCharArray();
				contador = 0;
				silabasIguais = true;
				/*!< L� uma linha no arquivo, e converte uma String para um vetor de Strings, que possuem as s�labas da palavra lida, e a compara com as s�labas da palavra a ser inserida*/
				for(int j = 0; j < numSilabasPalavra; j++)
				{
					inicioSilaba = contador;
					while(linha[contador] != '-' && linha[contador] != ' ')
						contador++;
					silabaLida = new String(linha, inicioSilaba, contador - inicioSilaba);
					contador++;
					if(silabaLida.equals(silabas[j]) == false)
					{
						silabasIguais = false;
						break;
					}
				}
				/*!<Verifica se as palavras s�o iguais*/
				if(silabasIguais)
				{
					br.close();
					bw.close();
					novo.delete();
					return false;
				}else
					bw.write(linha);bw.newLine();
			}
			/*!< Insere a nova palavra*/
			for(int i = 0; i < numSilabasPalavra - 1; i++)
				bw.write(silabas[i] + "-");
			bw.write(silabas[numSilabasPalavra - 1] + " ");
			for(int i = 0; i < (numTotalSilabas - numSilabasPalavra - 1); i++)
				bw.write(extras[i] + " ");
			bw.write(extras[numTotalSilabas - numSilabasPalavra - 1]);
			bw.newLine();
			
			/*< Insere as palavras apos a pr�xima palavra.*/
			numLinhasPuladas = numPalavras;
			if(posicaoIndice == indices.length - 1)
				numLinhasPuladas = 0;
			else
				numLinhasPuladas -= indices[posicaoIndice + 1];
			for(int i = 0; i < numLinhasPuladas; i++)
			{
				bw.write(br.readLine()); bw.newLine();
			}
			bw.write(br.readLine());
			/*< Fecha, deleta, e renomeia os arquivos*/
			br.close();
			bw.close();
			dados.delete();
			novo.renameTo(dados);
		}catch(IOException e)
		{
			e.fillInStackTrace();
		}
		return true;
	}
	
	// Utilizar a main para realizar testes na insercao.
	/*public static void main(String[] args) throws ArquivoException
	{
		String palavra[] = {"di", "ver", "g�n", "ci", "a"};
		String extras[] =  {"de", "si", "lhem"};
		Niveis n = Niveis.NIVEL3;
		ManipulaSilabas ma = null;
		Palavra recebida = null;
		
		try
		{
			ma = new ManipulaSilabas(n);
			if(ma.inserePalavra(palavra, extras) == true) System.out.println("Inseriu!");
			else System.out.println("N�o inseriu!");
		}catch(ArquivoException e)
		{
			e.getMessage();
			e.fillInStackTrace();
		}
		
		for(int i = 0; i < 30; i++)
		{
			do { recebida = ma.recebePalavra();} while(recebida == null);
			System.out.println(recebida.getPalavra());
		}
	}*/
}
