package anonymizer;

import java.util.Iterator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cette classe contient les r�gles d'anonymisation des donn�es
 */
public class AnonymizeRules {

	private static Logger logger = LogManager.getLogger(AnonymizeRules.class);
	
	private AnonymizeRules() {
		throw new IllegalStateException("Rules class");
	}

	/**
	 * Point d'entr�e pour savoir quelle r�gle d'anonymisation appliquer
	 *
	 * @param a le nom de la r�gle d'anonymisation � appliquer 
	 * @param b la donn�e � anonymiser
	 * @return la donn�e anonymiser
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
	 * Change les lettres d'une addresse de mani�re al�atoire
	 *
	 * @param b l'addresse � anonymiser
	 * @return l'addresse anonymis�e
	 */
	private static String randomLetterMail(String b) {
		logger.info("Start of random anonimization");
		StringBuilder rdmLetter = new StringBuilder();
		if(!b.contains("@"))
			return b;
		String firstPart = b.split("@")[0];
		String secondPart = b.split("@")[1];
		for (int i = 0; i < firstPart.length(); i++) {
			rdmLetter.append(RandomStringUtils.randomAlphabetic(1));			
		}
		logger.info("End of random anonimization");
		return String.format("%s@%s", rdmLetter.toString(), secondPart);
	}

	/**
	 * Change les lettres de mani�re al�atoire
	 *
	 * @param b le mot � anonymiser
	 * @return le mot anonymis�
	 */
	private static String randomLetter(String b) {
		StringBuilder rdmLetter = new StringBuilder();
		for (int i = 0; i < b.length(); i++) {
			rdmLetter.append(RandomStringUtils.randomAlphabetic(1));
		}
		return rdmLetter.toString();
	}
}
