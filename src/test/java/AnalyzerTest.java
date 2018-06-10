

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
    /*
     * =========================================
     * Part 1
     * =========================================
     */

    /**
     * Test if file exist.
     */
	@Test
	public void readFile_FileExist_True() {
		List<Sentence> listSentences = Analyzer.readFile("archivo.txt");
		assertTrue(!listSentences.isEmpty());
	}

    /**
     * Test if reads all frases from file.
     */
    @Test
    public void readFile_NumberSentences_CorrectNumber() {
        List<Sentence> listSentences = Analyzer.readFile("archivo.txt");
        assertEquals(6, listSentences.size());
    }

    /**
     * Test if file does not exist.
     */
	@Test
	public void readFile_Nofile_True() {
		List<Sentence> listSentences = Analyzer.readFile("no_existe_arcchivo.txt");
		assertTrue(listSentences.isEmpty());
	}

    /**
     * Test if file is empty.
     */
	@Test
	public void readFile_FileEmpty_Empty() {
		List<Sentence> listSentences = Analyzer.readFile("archivo_vacio.txt");
		assertEquals("", 0, listSentences.size());
	}

    /**
     * Test if file has the wrong format.
     */
	@Test
	public void readFile_WrongFormat_NumSentences() {
		List<Sentence> listSentences = Analyzer.readFile("archivo_formato_incorrecto.txt");
		assertEquals(3, listSentences.size());
	}

    /**
     * Test if score boundaries are correct.
     */
	@Test
	public void readFile_WorgScoreBoundaries_NumSentences() {
		List<Sentence> listSentences =  Analyzer.readFile("archivo_formato_limites.txt");
		assertEquals(2, listSentences.size());
	}



    /*
     * =========================================
     * Part 2
     * =========================================
     */

    /**
     * Test the right total number of counted words read from file.
     */
	@Test
	public void allWords_RightTotalNumWords_NumWords() {
		List<Sentence> lista = Analyzer.readFile("archivo.txt");
        Set<Word> listaPalabras = Analyzer.allWords(lista);
		assertEquals(40, listaPalabras.size());
	}


    /**
     * Test the right number of counted words. Ej. "java"
     */
	@Test
	public void allWords_RightNumberWords_NumberWords() {
		List<Sentence> listSentences = Analyzer.readFile("archivo.txt");
        Set<Word> listWords = Analyzer.allWords(listSentences);

		int count = 0;
		for (Word word : listWords) {
			if("java".equals(word.getText())){
				count = word.getCount();
			}
		}

		assertEquals(2, count);
	}

    /**
     * Test the right number of cummulative number of a word. Ej. "java".
     */
	@Test
	public void allWords_RightCummulativeNumber_NumberWords() {
		List<Sentence> lista = Analyzer.readFile("archivo.txt");
        Set<Word> listSentences = Analyzer.allWords(lista);

		int total = 0;
		for (Word word: listSentences) {
			if("java".equals(word.getText())){
				total = word.getTotal();
			}
		}
		assertEquals(4, total);
	}


	/*
	 * =========================================
	 * Part 3
	 * =========================================
	 */

    /**
     * Test if score's word is correct. Ej, "fun".
     */
	@Test
	public void calculateScores_RightScore_WordScore() {
		List<Sentence> listSentences = Analyzer.readFile("archivo.txt");
        Set<Word> listaPalabras = Analyzer.allWords(listSentences);
		Map<String, Double> mapWords = Analyzer.calculateScores(listaPalabras);

		double score = 0;
		for (Map.Entry<String, Double> entry : mapWords.entrySet()) {
			if ("fun".equals(entry.getKey())) {
				score = entry.getValue();
			}
		}
		assertEquals("0.8", String.valueOf(score));
	}

    /**
     * Test if if map is empty if an empty file is provided.
     */
	@Test
	public void calculateScores_EmptyMapEmptyFile_MapWords() {
		List<Sentence> listSentences = Analyzer.readFile("archivo_vacio.txt");
        Set<Word> listWords = Analyzer.allWords(listSentences);

		Map<String, Double> mapWords = Analyzer.calculateScores(listWords);

		assertEquals(0, mapWords.size());
	}
}
