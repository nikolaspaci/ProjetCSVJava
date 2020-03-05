package checker;


/**
 * Cette classe contient les r�gles de v�rification des donn�es
 */
public class CheckRules {

	private CheckRules() {
		throw new IllegalStateException("Rules class");
	}

	/**
	 * Point d'entr�e pour savoir quelle r�gle de v�rification appliquer
	 *
	 * @param rulesName le nom de la r�gle de v�rification � appliquer
	 * @param valuetoAnon la donn�e � v�rifier
	 * @return un bool�en pour savoir si la donn�e respecte la r�gle
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
	 * Verifie si l'�ge est correct
	 *
	 * @param age l'�ge � v�rifier
	 * @return true, si l'age est bon
	 */
	private static boolean isCorrectAge(Integer age) {
		return (age >= 0 && age <= 120);
	}
}
