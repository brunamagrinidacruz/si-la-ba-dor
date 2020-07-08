package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exception.ArquivoException;
import model.Palavra;

public class TestePalavra {

	@Test
	public void testGetPalavra() throws ArquivoException {
		String s =  "ca-be-ça te cam al";
		Palavra nova = new Palavra(s.toCharArray(), 3);
		assertEquals("cabeça", nova.getPalavra());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetSilabas() throws ArquivoException {
		String s =  "ca-be-ça te cam al";
		String expected[] = {"ca", "be", "ça"};
		Palavra nova = new Palavra(s.toCharArray(), 3);
		assertEquals(expected, nova.getSilabasPalavra());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetSilabasAdicionais() throws ArquivoException {
		String s =  "ca-be-ça te cam al";
		String expected[] = {"te", "cam", "al"};
		Palavra nova = new Palavra(s.toCharArray(), 3);
		assertEquals(expected, nova.getSilabasAdicionais());
	}
	
	@Test
	public void testGetNumSilabas() throws ArquivoException
	{

		String s =  "ca-be-ça te cam al";
		Palavra nova = new Palavra(s.toCharArray(), 3);
		assertEquals(3, nova.getNumSilabas());
	}
	
	@Test
	public void testGetNumLetras() throws ArquivoException
	{

		String s =  "ca-be-ça te cam al";
		Palavra nova = new Palavra(s.toCharArray(), 3);
		assertEquals(6, nova.getNumLetras());
	}
	
	@Test
	public void testGetNumTotalSilabas() throws ArquivoException
	{
		String s = "ca-be-ça te cam al";
		Palavra nova = new Palavra(s.toCharArray(), 3);
		assertEquals(6, nova.getNumTotalSilabas());
	}
	
	@Test
	public void TestGetSilabasCompleto() throws ArquivoException
	{
		/*
		 *  	Como o Vetor "silabasCompleto" é gerado aleatóriamente, podendo gerar vários casos,
		 *  buscaremos no vetor, cada silaba esperada, se todas silabas forem encontradas, o teste
		 *  será marcado como certo.
		 */
		
		String s = "ca-be-ça te cam al";
		Palavra nova = new Palavra(s.toCharArray(), 3);
		String[] silabas = nova.getSilabasCompleto();
		String[] silabasEsperadas = {"ca", "be", "ça", "te", "cam", "al"};
		boolean test = false;
		
		for(int i = 0; i < nova.getNumTotalSilabas(); i++)
		{
			test = false;
			for(int j = 0; j < nova.getNumTotalSilabas(); j++)
			{
				if(silabas[i].equals(silabasEsperadas[j]))
					test = true;
			}
			
			if(test == false)
				break;
		}
		
		assertTrue(test);
	}
	
	@Test
	public void testToSting() throws ArquivoException
	{

		String s =  "ca-be-ça te cam al";
		String expected = "NumSilabas: 3\nNumTotalSilabas: 6\nNumLetras: 6\nPalavra: cabeça\nSilabas Extras: te cam al ";
		Palavra nova = new Palavra(s.toCharArray(), 3);
		assertEquals(expected, nova.toString());
	}
	
	@Test(expected=ArquivoException.class)
	public void test01() throws ArquivoException
	{
		new Palavra(".".toCharArray(), 7);
	}
	
	@Test(expected=ArquivoException.class)
	public void test02() throws ArquivoException
	{
		new Palavra(null, 7);
	}
	@Test
	public void test03() throws ArquivoException
	{
		Palavra nova = new Palavra("re-la-ci-o-na-men-to pa".toCharArray(), 7);
		assertEquals("relacionamento", nova.getPalavra());
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected=ArquivoException.class)
	public void test04() throws ArquivoException
	{
		Palavra nova = new Palavra("a-le-a-to-ri-e-da-de ".toCharArray(), 8);
		assertEquals(null, nova.getSilabasAdicionais());
	}
	
	@Test(expected=ArquivoException.class)
	public void test05() throws ArquivoException
	{
		new Palavra("re".toCharArray(), 7);
	}

	@Test(expected=ArquivoException.class)
	public void test06() throws ArquivoException
	{
		new Palavra("bo-la bom tram le i".toCharArray(), 3);
	}
	
	@Test(expected=ArquivoException.class)
	public void test07() throws ArquivoException
	{
		new Palavra("pa-lha-ço lia so co".toCharArray(), 2);
	}
	
	@Test(expected=ArquivoException.class)
	public void test08() throws ArquivoException
	{
		new Palavra("pa-lha-ço lia so co pra".toCharArray(), 3);
	}
}
