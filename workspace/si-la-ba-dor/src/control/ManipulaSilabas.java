package control;
import java.io.*;

import model.*; 

import java.util.LinkedList;
import java.util.Queue;

import exception.ArquivoException;  

public class ManipulaSilabas {
		
	static int indices[];   // Armazena os índices de busca, referente a cada número de sílabas.
	static int numPalavras; // Armazena o número de palavras salvas no arquivo.
	static Niveis dificuldade;
	static String nomeArquivo;
	// Fila para armazenar as últimas quatro palavras escolhidas, utilizada para impedir repetições.
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
		 * 		A condicional abaixo verificá qual nível foi escolhido para selecionar o arquivo selecionado
		 * 	e instanciar o vetor "indices", visto que, como cada nível possui um valor diferente de números de
		 *  sílabas selecionados, seu tamanho será diferente para cada arquivo.
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
			// Lê e armazena o cabeçalho do arquivo.
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
		 * Método para inserir e remover na fila, mantendo sempre quatro ou menos elementos.
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
		 * 		Método que seleciona um número aleatório de silabas com base no nível e 
		 *  percorre o arquivo, e escolhe uma palavra aleatória com a quantidade de sílabas.
		 */
		
		int silabas = 0;
		int numSilabas = 0; // Armazena o número de sílabas da palavra.
		int contador = 0;   // Contador, que ao atingir um determinado valor, interrompe a excecução do algoritmo do cache pois caso, num arquivo, um determinado número de sílabas seja menor que o tamanho do cache, o programa entrará em loop infinito.
		
		switch(dificuldade) 
		{
			case NIVEL1:
				/*!< getIndRand irá retornar valores entre [0, 1] então ao somar 2, os valores irão variar entre [2, 3] */
				silabas = r.getIntRand(2);
				numSilabas = silabas + 2;
				break;
			case NIVEL2:
				/*!< getIndRand irá retornar valores entre [0, 2] então ao somar 2, os valores irão variar entre [2, 4] */
				silabas = r.getIntRand(3);
				numSilabas = silabas + 2;
				break;
			case NIVEL3:
				/*!< getIndRand irá retornar valores entre [0, 2] então ao somar 5, os valores irão variar entre [5, 7] */
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
		int palavraEscolhida; 	 // Variável que armazenará quantas linhas deverão ser puladas para chegar a palavra escolhida aleatoriamente.

		if(numSilabas < 2 || numSilabas > 7) return null;
		
		try(FileReader fr = new FileReader(dados);
			BufferedReader br = new BufferedReader(fr)){
			
			// Pula o cabeçalho:
			for(int i = 0; i < tamanhoCabecalho; i++)
				br.readLine();
			// Agora devemos, ou não, pular as linhas no arquivo para chegar no numero de silabas solicitado.
			for(int i = 0; i < indices[silabas]; i++)
				br.readLine();
			// Atribui, a variável palavraEscolhida, o número de palavras com a quantidade e sílabas escolhida
			if(silabas == indices.length - 1) palavraEscolhida = (numPalavras -indices[silabas]);
			else palavraEscolhida = (indices[silabas+1] - indices[silabas]);
			
			// Define, aleatoriamente, quantas linhas deverão ser puladas para escolher uma palavra.
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
		 *  	Método para inserir uma palavra num arquivo, nota-se que a inserção será feita no
		 *  arquivo que foi escolhido na instanciação da classe e está armazenado no atributo "dificuldade".
		 *  	Como queremos manter a ordenação de sílabas, e para evitar sobrescrição de dados, a inserção
		 *  será implementada de forma que, será copiado um arquivo temporário "tmp.txt", no qual copiaremos o
		 *  arquivo original. Caso a inserção seja realizada corretamente, o arquivo original será apagado e
		 *  o arquivo temporário será renomeado para ser usado como o original. Caso a palavra a ser inserida
		 *  seja encontrada durante a inserção, apagaremos o arquivo temporário e o arquivo original estará
		 *  sem modificações.
		 *  
		 *  	OBS: O arquivo é percorrido previamente para impedir a inserção de uma palavra repetida.
		 *  
		 *  String[] silabas - Vetor que contém as sílabas constituintes da palavra a ser inserida.
		 *  String[] extras  - Vetor que contém as sílabas a serem embaralhadas juntos com a nova palavra.
		 */
		
		File dados = new File(nomeArquivo);
		File novo  = new File("tmp.txt");
		int numSilabasPalavra;	// Armazena o número de sílabas da palavra a ser inserida e, posteriormente, assume uma posição referente ao vetor "indices".
		int numPalavras;		// Armazena o número total de palavras no arquivo.
		int aux; 				// Variável auxiliar para armazenar algum valor a ser alterado e reinserido no arquivo.
		int numLinhasPuladas;   // Armazena o número de linhas a serem puladas até alcançar a posição em que a palavra deve ser inserida.
		char linha[];		    // Conjunto de caracteres que armazena uma linha do arquivo original.
		int inicioSilaba; 	    // Variável que armazena a posição da primeira letra de uma determinada sílaba no vetor "linhas".
		int contador;	  	    // Contador utilizado para percorrer o vetor "linhas".
		boolean silabasIguais;  // Variável booleana que ajuda a verificar se a palavra a ser inserida e uma dada palavra do arquivo original são iguais.
		int numTotalSilabas = 0;// Variável que armazena o número total de sílabas (constituintes e extras), devem ter sido fornecidas.
		int posicaoIndice = 0;
		String silabaLida;
		numSilabasPalavra = silabas.length;
		
		/*!< Verifica se os parâmetros foram passados corretamente.*/
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
		/*!<Agora deve-se garantir que a palavra ainda não foi inserida no arquivo.*/
		try(FileReader fr = new FileReader(dados);
				FileWriter fw = new FileWriter(novo);
				BufferedReader br = new BufferedReader(fr);
				BufferedWriter bw = new BufferedWriter(fw)) {
			
			/*!<Atualiza o cabeçalho do arquivo.*/
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
			/*!< Pula o número de linhas definidas.*/
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

			/*!<Realiza uma busca nas palavras com o mesmo número de sílabas que a palavra a ser inserida, caso esta palavra seja encontrada, a inserção é interrompida.*/
			for(int i = 0; i < numLinhasPuladas; i++)
			{
				linha = br.readLine().toCharArray();
				contador = 0;
				silabasIguais = true;
				/*!< Lê uma linha no arquivo, e converte uma String para um vetor de Strings, que possuem as sílabas da palavra lida, e a compara com as sílabas da palavra a ser inserida*/
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
				/*!<Verifica se as palavras são iguais*/
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
			
			/*< Insere as palavras apos a próxima palavra.*/
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
	
	// Utilizar a main para realizar testes na inserção.
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
