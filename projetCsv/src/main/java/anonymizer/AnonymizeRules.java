package anonymizer;

import java.util.Iterator;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Cette classe contient les règles d'anonymisation des données
 */
public class AnonymizeRules {


	private AnonymizeRules() {
		throw new IllegalStateException("Rules class");
	}

	/**
	 * Point d'entrée pour savoir quelle règle d'anonymisation appliquer
	 *
	 * @param a le nom de la règle d'anonymisation à appliquer 
	 * @param b la donnée à anonymiser
	 * @return la donnée anonymiser
	 */
	public static Object anonymiseHub(String a, Object b) {
		switch (a) {
		case "RANDOM_LETTER_FOR_LOCAL_PART":
			return randomLetterMail((String) b);
		case "RANDOM_LETTER":
			return randomLetter((String) b);
		default:
			break;
		}
		return null;
	}

	/**
	 * Change les lettres d'une addresse de manière aléatoire
	 *
	 * @param b l'addresse à anonymiser
	 * @return l'addresse anonymisée
	 */
	private static String randomLetterMail(String b) {
		StringBuilder rdmLetter = new StringBuilder();
		if(!b.contains("@"))
			return b;
		String firstPart = b.split("@")[0];
		String secondPart = b.split("@")[1];
		for (int i = 0; i < firstPart.length(); i++) {
			rdmLetter.append(RandomStringUtils.randomAlphabetic(1));
		}
		return String.format("%s@%s", rdmLetter.toString(), secondPart);
	}

	/**
	 * Change les lettres de manière aléatoire
	 *
	 * @param b le mot à anonymiser
	 * @return le mot anonymisé
	 */
	private static String randomLetter(String b) {
		StringBuilder rdmLetter = new StringBuilder();
		for (int i = 0; i < b.length(); i++) {
			rdmLetter.append(RandomStringUtils.randomAlphabetic(1));
		}
		return rdmLetter.toString();
	}
}
