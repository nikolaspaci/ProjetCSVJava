
import static org.junit.Assert.*;

import org.junit.Test;

import anonymizer.AnonymizeRules;

public class TestAnonymMethods {

	@Test
	public void testRandomLetterMail() {
		String mail="nikolaspaci@gmail.com";
		String rule="RANDOM_LETTER_FOR_LOCAL_PART";
		String anonymail=(String) AnonymizeRules.anonymiseHub(rule,mail);
		System.out.println("Mail anonyme : "+anonymail);
		String firstPartMailAnon=anonymail.substring(0,anonymail.indexOf("@"));
		String secondPartMailAnon=anonymail.substring(anonymail.indexOf("@"));
		String firstPartMail=mail.substring(0,mail.indexOf("@"));
		String secondPartMail=mail.substring(mail.indexOf("@"));
		assertTrue(firstPartMail!=firstPartMailAnon);
		System.out.println("Premiere partie Anonyme: "+firstPartMailAnon+" et normal: "+firstPartMail);
		assertEquals(secondPartMail,secondPartMailAnon);
		System.out.println("Seconde partie Anonyme: "+secondPartMailAnon+" et normal: "+secondPartMail);
	}
	
	
	@Test
	public void testRandomLetter() {
		String word="nikolaspaci";
		String rule="RANDOM_LETTER";
		String anonymWord=(String) AnonymizeRules.anonymiseHub(rule,word);
		System.out.println("Mot anonyme : "+anonymWord);
		assertTrue(word!=anonymWord);		
	}

}
