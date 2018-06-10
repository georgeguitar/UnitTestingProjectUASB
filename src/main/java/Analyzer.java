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
		List<Sentence> listSentences = new ArrayList<Sentence>();
		File fichero = new File(filename);
		Scanner s = null;

		try {
			s = new Scanner(fichero);

			while (s.hasNextLine()) {
				String linea = s.nextLine(); 	// Guardamos la linea en un String
				System.out.println(linea);      // Imprimimos la linea

				String score = linea.substring(0, linea.indexOf(" "));

				Integer puntaje;
				if (score.matches("^[+-]?[0-2]{1}$")) {
					puntaje = Integer.valueOf(score);
					if (puntaje >= -2 && puntaje <= 2) {
						String text = linea.substring(linea.indexOf(" ") + 1, linea.length());
						Sentence sentence = new Sentence(puntaje, text);
						listSentences.add(sentence);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}

		return listSentences;

	}

	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		Set<Word> listaPalabras = new TreeSet<Word>();
		for (Sentence sentence : sentences) {
			String[] palabras = sentence.text.split(" ");
			for (String palabra : palabras) {
				Word word = new Word(palabra.toLowerCase().trim());
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
		Map<String, Double> mapa = new HashMap<String, Double>();
		for (Word word: words) {
			mapa.put(word.getText(), word.calculateScore());
		}

		return mapa;

	}

}
