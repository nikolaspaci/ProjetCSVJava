package anonymizer;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cette classe contient les règles d'anonymisation des données
 */
public class AnonymizeRules {

	private static Logger logger = LogManager.getLogger(AnonymizeRules.class);
	
	private AnonymizeRules() {
		throw new IllegalStateException("Rules class");
	}

	/**
	 * Point d'entrée pour savoir quelle règle d'anonymisation appliquer
	 *
	 * @param rulesName le nom de la règle d'anonymisation à appliquer 
	 * @param valueToAnon la donnée à anonymiser
	 * @return la donnée anonymiser
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
	 * Change les lettres d'une addresse de manière aléatoire
	 *
	 * @param valueToRdm l'addresse à anonymiser
	 * @return l'addresse anonymisée
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
	 * Change les lettres de manière aléatoire
	 *
	 * @param valueToRandom le mot à anonymiser
	 * @return le mot anonymisé
	 */
	private static String randomLetter(String valueToRandom) {
		StringBuilder rdmLetter = new StringBuilder();
		for (int i = 0; i < valueToRandom.length(); i++) {
			rdmLetter.append(RandomStringUtils.randomAlphabetic(1));
		}
		return rdmLetter.toString();
	}
}
