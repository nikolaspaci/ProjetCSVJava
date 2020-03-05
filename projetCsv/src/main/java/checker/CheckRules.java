package checker;


/**
 * Cette classe contient les règles de vérification des données
 */
public class CheckRules {

	private CheckRules() {
		throw new IllegalStateException("Rules class");
	}

	/**
	 * Point d'entrée pour savoir quelle règle de vérification appliquer
	 *
	 * @param rulesName le nom de la règle de vérification à appliquer
	 * @param valuetoAnon la donnée à vérifier
	 * @return un booléen pour savoir si la donnée respecte la règle
	 */
	public static boolean checkHub(String rulesName, Object valuetoAnon) {
		switch (rulesName) {
		case "BE_AN_AGE":
			return isCorrectAge((Integer) valuetoAnon);
		case "BE_AN_DAUPHINE_EMAIL":
			return isDauphineMail((String) valuetoAnon);
		case "BE_AN_EMAIL":
			return isMail((String) valuetoAnon);
		default:
			break;
		}
		return true;
	}

	/**
	 * Verifie si c'est un mail
	 *
	 * @param valueToCheck le mail
	 * @return true, si c'est un mail
	 */
	private static boolean isMail(String valueToCheck) {
		String regexMail = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";
		return valueToCheck.matches(regexMail);
	}

	/**
	 * Verifie si c'est un mail de Dauphine
	 *
	 * @param mailToCheck l'addresse mail
	 * @return true, si c'est une addresse de dauphine
	 */
	private static boolean isDauphineMail(String mailToCheck) {
		String regexMailDau1 = "^(.+)@dauphine.eu$";
		String regexMailDau2 = "^(.+)@dauphine.psl.eu$";
		String regexMailDau3 = "^(.+)@lamsade.dauphine.fr$";
		return (mailToCheck.matches(regexMailDau1) || mailToCheck.matches(regexMailDau2) || mailToCheck.matches(regexMailDau3));

	}

	/**
	 * Verifie si l'âge est correct
	 *
	 * @param age l'âge à vérifier
	 * @return true, si l'age est bon
	 */
	private static boolean isCorrectAge(Integer age) {
		return (age >= 0 && age <= 120);
	}
}
