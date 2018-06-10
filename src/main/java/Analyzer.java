/**
 * Elaborado por: Juan Dirceu Navarro Arias
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Analyzer {

	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		List<Sentence> listaFrases = new ArrayList<Sentence>();
		File archivoF = new File(filename);
		Scanner archivoTexto = null;

		try {
			archivoTexto = new Scanner(archivoF);

			while (archivoTexto.hasNextLine()) {
				String linea = archivoTexto.nextLine();
				if (!linea.trim().isEmpty()) {
                    String score = linea.substring(0, linea.indexOf(" "));
                    Integer puntaje = 0;
                    if (score.matches("^[+-]?[0-2]{1}$")) {
                        puntaje = Integer.valueOf(score);
                        if (puntaje >= -2 && puntaje <= 2) {
                            String text = linea.substring(linea.indexOf(" ") + 1, linea.length());
                            Sentence sentence = new Sentence(puntaje, text);
                            listaFrases.add(sentence);
                        }
                    }
                }
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		} finally {
			// Si no se puede cerrar el archivo por si se borró despues de estar abierto
			try {
				if (archivoTexto != null)
					archivoTexto.close();
			} catch (Exception ex2) {
				System.out.println("Error: " + ex2.getMessage());
			}
		}
		return listaFrases;
	}

	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		Set<Word> listaPalabras = new TreeSet<Word>();
		for (Sentence sentence : sentences) {
			String[] palabras = sentence.text.split(" ");
			// Search word by word in list of sentences.
			for (String palabraBuscar : palabras) {
				Word word = new Word(palabraBuscar.toLowerCase().trim());
				for (Sentence sentence1 : sentences) {
					if (sentence1.getText().toLowerCase().trim().contains(word.getText())) {
						word.increaseTotal(sentence1.getScore());
					}
				}
				listaPalabras.add(word);
			}
		}
		return listaPalabras;
	}

	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
		Map<String, Double> mapaPalabras = new HashMap<String, Double>();
		for (Word word: words) {
			mapaPalabras.put(word.getText(), word.calculateScore());
		}
		return mapaPalabras;
	}

}
