package anonymizer;


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
	 * @param rulesName le nom de la r�gle d'anonymisation � appliquer 
	 * @param valueToAnon la donn�e � anonymiser
	 * @return la donn�e anonymiser
	 */
	public static Object anonymiseHub(String rulesName, Object valueToAnon) {
		switch (rulesName) {
		case "RANDOM_LETTER_FOR_LOCAL_PART":
			return randomLetterMail((String) valueToAnon);
		case "RANDOM_LETTER":
			return randomLetter((String) valueToAnon);
		default:
			break;
		}
		return null;
	}

	/**
	 * Change les lettres d'une addresse de mani�re al�atoire
	 *
	 * @param valueToRdm l'addresse � anonymiser
	 * @return l'addresse anonymis�e
	 */
	private static String randomLetterMail(String valueToRdm) {
		logger.info("Start of random anonimization");
		StringBuilder rdmLetter = new StringBuilder();
		if(!valueToRdm.contains("@"))
			return valueToRdm;
		String firstPart = valueToRdm.split("@")[0];
		String secondPart = valueToRdm.split("@")[1];
		for (int i = 0; i < firstPart.length(); i++) {
			rdmLetter.append(RandomStringUtils.randomAlphabetic(1));			
		}
		logger.info("End of random anonimization");
		return String.format("%s@%s", rdmLetter.toString(), secondPart);
	}

	/**
	 * Change les lettres de mani�re al�atoire
	 *
	 * @param valueToRandom le mot � anonymiser
	 * @return le mot anonymis�
	 */
	private static String randomLetter(String valueToRandom) {
		StringBuilder rdmLetter = new StringBuilder();
		for (int i = 0; i < valueToRandom.length(); i++) {
			rdmLetter.append(RandomStringUtils.randomAlphabetic(1));
		}
		return rdmLetter.toString();
	}
}
