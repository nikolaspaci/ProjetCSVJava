

import static org.junit.Assert.*;

import org.junit.Test;

import checker.CheckRules;

public class TestCheckMethods {

	@Test
	public void testIsMail() {
		String rule="BE_AN_EMAIL";
		String mailToTestGood="tom.moore@gmail.com";
		String mailToTestBad="tom.mooregmail.com";
		assertTrue(CheckRules.checkHub(rule,mailToTestGood));
		assertFalse(CheckRules.checkHub(rule,mailToTestBad));

	}
	
	@Test
	public void testIsDauphineMail() {
		String rule="BE_AN_DAUPHINE_EMAIL";
		String mailToTestGood="tom.moore@dauphine.eu";
		String mailToTestGood2="tom.moore@lamsade.dauphine.fr";
		String mailToTestGood3="tom.moore@dauphine.psl.eu";
		String mailToTestBad="tom.moore@gmail.com";
		String mailToTestBad2="tom.moore@dauphine.fr";
		String mailToTestBad3="tom.moore@daufine.psl.eu";
		assertTrue(CheckRules.checkHub(rule,mailToTestGood));
		assertTrue(CheckRules.checkHub(rule,mailToTestGood2));
		assertTrue(CheckRules.checkHub(rule,mailToTestGood3));
		assertFalse(CheckRules.checkHub(rule,mailToTestBad));
		assertFalse(CheckRules.checkHub(rule,mailToTestBad2));
		assertFalse(CheckRules.checkHub(rule,mailToTestBad3));
	}
	
	@Test
	public void testIsCorrectAge() {
		String rule="BE_AN_AGE";
		int AgeGood=23;
		int AgeBad=-1;
		int AgeBad2=213;
		assertTrue(CheckRules.checkHub(rule,AgeGood));
		assertFalse(CheckRules.checkHub(rule,AgeBad));
		assertFalse(CheckRules.checkHub(rule,AgeBad2));
	}

}
