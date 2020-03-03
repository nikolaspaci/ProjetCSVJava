package checker;

import java.util.regex.Pattern;

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
	 * @param a le nom de la r�gle de v�rification � appliquer
	 * @param b la donn�e � v�rifier
	 * @return un bool�en pour savoir si la donn�e respecte la r�gle
	 */
	public static boolean checkHub(String a, Object b) {
		switch (a) {
		case "BE_AN_AGE":
			return isCorrectAge((Integer) b);
		case "BE_AN_DAUPHINE_EMAIL":
			return isDauphineMail((String) b);
		case "BE_AN_EMAIL":
			return isMail((String) b);
		default:
			break;
		}
		return true;
	}

	/**
	 * Verifie si c'est un mail
	 *
	 * @param b le mail
	 * @return true, si c'est un mail
	 */
	private static boolean isMail(String b) {
		String regexMail = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$";
		return b.matches(regexMail);
	}

	/**
	 * Verifie si c'est un mail de Dauphine
	 *
	 * @param b l'addresse mail
	 * @return true, si c'est une addresse de dauphine
	 */
	private static boolean isDauphineMail(String b) {
		String regexMailDau1 = "^(.+)@dauphine.eu$";
		String regexMailDau2 = "^(.+)@dauphine.psl.eu$";
		String regexMailDau3 = "^(.+)@lamsade.dauphine.fr$";
		return (b.matches(regexMailDau1) || b.matches(regexMailDau2) || b.matches(regexMailDau3));

	}

	/**
	 * Verifie si l'�ge est correct
	 *
	 * @param b l'�ge � v�rifier
	 * @return true, si l'age est bon
	 */
	private static boolean isCorrectAge(Integer b) {
		return (b >= 0 && b <= 120);
	}
}
