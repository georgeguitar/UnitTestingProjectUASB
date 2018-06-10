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
		boolean error = false;
		
		// Fichero del que queremos leer
		File fichero = new File(filename);
		Scanner s = null;

		try {
			// Leemos el contenido del fichero
			System.out.println("... Leemos el contenido del fichero ...");
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String linea = s.nextLine(); 	// Guardamos la linea en un String
				System.out.println(linea);      // Imprimimos la linea
				
				String score = linea.substring(0, linea.indexOf(" "));
				
				/**
				 * http://txt2re.com/index-java.php3?s=-2%20I%20would%20have%20preferred%20an%20easier%20assignment&11&17&7
				 * https://regexr.com/
				 * 
				 *     String re1="([-+]\\d+)";	// Integer Number 1
					    String re2="(\\s+)";	// White Space 1
					    String re3=".*?";	// Non-greedy match on filler
					    String re4="((?:[a-z][a-z]+))";	// Word 1
				 * 
				 * **/
				
//				if (score.matches("-?\\d+(\\.\\d+)?")) {
				
				Integer puntaje;
//				if (score.matches("[-+]\\d+")) {
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
    			listaPalabras.add(word);
    		}
    	}
    	for (Word palabra : listaPalabras) {
    		String palabraBuscar = palabra.getText();
        	for (Sentence sentence : sentences) {
        		String[] palabras = sentence.text.split(" ");
        		for (String palabr : palabras) {
        			if (palabraBuscar.equals(palabr.toLowerCase().trim())) {
        				palabra.increaseTotal(sentence.getScore());
        			}
        		}
        		
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
