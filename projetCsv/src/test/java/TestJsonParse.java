

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.gson.stream.JsonReader;

import column.AnonymeColumn;
import column.DescriptorColumn;
import column.VerifColumn;
import utils.JsonUtils;

public class TestJsonParse {

	@Test
	public void testgetCheckingHeader() {
		String pathDesc= getClass().getClassLoader().getResource("verifCsv.json").getFile();
		List<VerifColumn>lDescCsv=JsonUtils.getCheckingHeader(pathDesc);
		assertFalse(lDescCsv.size()==4);
		assertTrue(lDescCsv.size()==3);
		assertEquals("AGE",lDescCsv.get(0).getName());
		assertEquals("BE_AN_AGE",lDescCsv.get(0).getShould()[0]);
		assertEquals("EMAIL_PRO",lDescCsv.get(1).getName());
		assertEquals("BE_AN_EMAIL",lDescCsv.get(1).getShould()[0]);
		assertEquals("BE_AN_DAUPHINE_EMAIL",lDescCsv.get(1).getShould()[1]);
		assertEquals("EMAIL_PERSO",lDescCsv.get(2).getName());
		assertEquals("BE_AN_EMAIL",lDescCsv.get(2).getShould()[0]);
	}

	@Test
	public void testgetCsvHeader() {
		String pathDesc= getClass().getClassLoader().getResource("DescriptorCsv.json").getFile();
		List<DescriptorColumn>lDescCsv=JsonUtils.getCsvHeader(pathDesc);
		assertFalse(lDescCsv.size()==4);
		assertTrue(lDescCsv.size()==5);
		assertEquals("NOM",lDescCsv.get(0).getName());
		assertEquals("STRING",lDescCsv.get(0).getDataType());
		assertEquals("DATE_DE_NAISSANCE",lDescCsv.get(2).getName());
		assertEquals("STRING",lDescCsv.get(2).getDataType());
	}
	
	@Test
	public void testGetAnonymHeader() {
		String pathDesc= getClass().getClassLoader().getResource("AnonymeCsv.json").getFile();
		List<AnonymeColumn>lDescCsv=JsonUtils.getAnonymHeader(pathDesc);
		assertFalse(lDescCsv.size()==4);
		assertTrue(lDescCsv.size()==2);
		assertEquals("NOM",lDescCsv.get(0).getName());
		assertEquals("RANDOM_LETTER",lDescCsv.get(0).getChangeTo());
		assertEquals("EMAIL_PERSO",lDescCsv.get(1).getName());
		assertEquals("RANDOM_LETTER_FOR_LOCAL_PART",lDescCsv.get(1).getChangeTo());
		
	}
}
