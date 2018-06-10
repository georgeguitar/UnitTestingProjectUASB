

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class AnalyzerTest {
	//readFile
	@Test
	public void testNoExisteArchivo() {
		//cuando el arcchivo no se pueda abrir
		List<Sentence> lista = new ArrayList<Sentence>();
		lista = Analyzer.readFile("no_existe_arcchivo.txt");
		assertTrue(lista.isEmpty());
	}

	@Test
	public void testReadFileEmpty() {
		List<Sentence> ListaVaciaResult=Analyzer.readFile("archivo_vacio.txt");
		assertEquals("", 0, ListaVaciaResult.size());
	}

	@Test
	public void testFormatoIncorrecto() {
		List<Sentence> lista = Analyzer.readFile("archivo_formato_incorrecto.txt");
		assertEquals(3, lista.size());
	}

	@Test
	public void testFormatoIncorrectoLimites() {
		List<Sentence> lista = Analyzer.readFile("archivo_formato_limites.txt");
		assertEquals(2, lista.size());
	}



	/*
	 * Implement this method in Part 2
	 */
	//allWords
	//el numero de palabras sea correcto
	@Test
	public void testNumeroPalabras() {
		Set<Word> listaPalabras = new TreeSet<Word>();
		List<Sentence> lista = new ArrayList<Sentence>();
		lista = Analyzer.readFile("archivo.txt");
		listaPalabras = Analyzer.allWords(lista);
		assertEquals(40, listaPalabras.size());
	}


	//cantidad de apariciones.
	@Test
	public void testDatosCorrectos() {
		Set<Word> listaPalabras = new TreeSet<Word>();
		List<Sentence> lista = new ArrayList<Sentence>();
		lista = Analyzer.readFile("archivo.txt");
		listaPalabras = Analyzer.allWords(lista);

		int count = 0;
		for (Word palabra : listaPalabras) {
			if("java".equals(palabra.getText())){
				count = palabra.getCount();
			}
		}

		assertEquals(2, count);
	}

	//acumulativo
	@Test
	public void testDatosCorrectosAcumulativo() {
		Set<Word> listaPalabras = new TreeSet<Word>();
		List<Sentence> lista = new ArrayList<Sentence>();
		lista = Analyzer.readFile("archivo.txt");
		listaPalabras = Analyzer.allWords(lista);

		int total = 0;
		for (Word palabra : listaPalabras) {
			if("java".equals(palabra.getText())){
				total = palabra.getTotal();
			}
		}
		assertEquals(4, total);
	}


	/*
	 * Implement this method in Part 3
	 */
	@Test
	public void testScore() {
		Set<Word> listaPalabras = new TreeSet<Word>();
		List<Sentence> lista = new ArrayList<Sentence>();
		lista = Analyzer.readFile("archivo.txt");
		listaPalabras = Analyzer.allWords(lista);

		Map<String, Double> mapa = new HashMap<String, Double>();
		mapa = Analyzer.calculateScores(listaPalabras);

		double score = 0;
		for (Map.Entry<String, Double> entry : mapa.entrySet()) {
			if ("fun".equals(entry.getKey())) {
				score = entry.getValue();
			}
		}
		assertEquals("0.8", String.valueOf(score));
	}

	@Test
	public void testMapaVacio() {
		Set<Word> listaPalabras = new TreeSet<Word>();
		List<Sentence> lista = new ArrayList<Sentence>();
		lista = Analyzer.readFile("archivo_vacio.txt");
		listaPalabras = Analyzer.allWords(lista);

		Map<String, Double> mapa = new HashMap<String, Double>();
		mapa = Analyzer.calculateScores(listaPalabras);


		assertEquals(0, mapa.size());
	}
}
