package control;

import java.io.*;
import model.*;
import java.util.LinkedList;
import java.util.Queue;  

public class ManipulaArquivo {
		
	static int indices[];   // Armazena os índices de busca, referente a cada número de sílabas.
	static int numPalavras; // Armazena o número de palavras salvas no arquivo.
	static int dificuldade;
	static String nomeArquivo;
	// Fila para armazenar as últimas quatro palavras escolhidas, utilizada para impedir repetições.
	private Queue<Integer> cache = new LinkedList<Integer>(); 
	
	private Random r;
	
	public ManipulaArquivo(int nivel)
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
			case 1:
				nomeArquivo = "nivel1.txt";
				indices = new int[2];
				break;
			case 2:
				nomeArquivo = "nivel2.txt";
				indices = new int[3];
				break;
			case 3:
				nomeArquivo = "nivel3.txt";
				indices = new int[3];
				break;
			default:
				nomeArquivo = null;
				indices = null;
				numPalavras = 0;
				return;
		}
		
		dados = new File(nomeArquivo);
		
		try(FileReader fr = new FileReader(dados);
			BufferedReader br = new BufferedReader(fr)){
			// Lê e armazena o cabeçalho do arquivo.
			numPalavras   = Integer.parseInt(br.readLine());
			
			int tamanho = indices.length;
			for(int i = 0; i < tamanho; i++)
				indices[i] = Integer.parseInt(br.readLine());
			
		}catch(IOException e)
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
	
	public Palavra recebePalavra(int silabas)
	{
		/*
		 * 		Método que, dado um número de sílabas, percorre o arquivo, e escolhe uma palavra aleatória
		 * 	da quantidade de sílabas fornecida.
		 */
		
		if(nomeArquivo == null) return null;
		
		Palavra nova = null;     // Contem o objeto "palavra" a ser retornado.
		File dados = new File(nomeArquivo);
		int tamanhoCabecalho = 0;// Armazena o tamanho do cabeçalho.
		int numSilabas;          // Armazena o número de sílabas da palavra.	
		int palavraEscolhida; 	 // Variável que armazenará quantas linhas deverão ser puladas para chegar a palavra escolhida aleatoriamente.
		
		if(silabas < 2 || silabas > 7) return null;

		numSilabas = silabas;
		
		// A condicional abaixo altera o número de sílabas, para a variável para acessar o vetor "indices".
		switch(dificuldade)
		{
			case 1:
				silabas -= 2;
				tamanhoCabecalho = 3;
				break;
			case 2:
				silabas -= 2;
				tamanhoCabecalho = 4;
				break;
			case 3:
				silabas -= 5;
				tamanhoCabecalho = 4;
				break;
		}
				
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
			do
			{
				palavraEscolhida = r.getIntRand(palavraEscolhida);
			}while(cache.contains(palavraEscolhida) == true);
			
			// Insere na Cache a palavra escolhida.
			atualizaCache(palavraEscolhida);
			
			// Pula a quantidade definida de linhas
			for(int i = 0; i < palavraEscolhida; i++)
				br.readLine();
			
			// Instancia o objeto a ser retornado.
			nova = new Palavra(br.readLine().toCharArray(), numSilabas);			
		}catch(IOException e)
		{
			e.fillInStackTrace();
		}
		
		return nova;
	}
}
