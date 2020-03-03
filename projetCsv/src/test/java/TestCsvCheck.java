

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.univocity.parsers.common.record.Record;

import checker.CsvChecker;
import column.AnonymeColumn;
import utils.CsvUtils;

public class TestCsvCheck {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void testCheck() throws IOException {
		final File tempFile = tempFolder.newFile("testCheck.csv");
		List<String> resWait = Arrays.asList("Koudossou, 22, 03/11/1998, Nikolas@dauphine.eu, nikolaspaci@gmail.com",
				"Moore, 22, 03/11/1998, Nikolas@dauphine.eu, nikolaspaci@dauphine.eu");
		List<String> resNotWait = Arrays.asList("Koudossou, 22, 03/11/1998, Nikolas@dauphine.eu, nikolaspaci@gmail.com",
				"Spaci, 22, 03/11/1998, Nikolas@dauphine.eu, nikolaspaci@dauphine.eu");
		String pathExemple = getClass().getClassLoader().getResource("exemple.csv").getFile();
		String pathCheckRules = getClass().getClassLoader().getResource("verifCsv.json").getFile();
		String pathDescCsv = getClass().getClassLoader().getResource("DescriptorCsv.json").getFile();
		String outputCheckCsv = tempFile.getPath();
		List<Record> listRecordCsv = CsvUtils.getCsvRecords(pathExemple);
		CsvChecker csvCheck = new CsvChecker(pathDescCsv, pathCheckRules);
		csvCheck.check(listRecordCsv, outputCheckCsv);
		List<Record> listCheck = CsvUtils.getCsvRecords(outputCheckCsv);
		assertEquals(listCheck.toString(), resWait.toString());
		assertNotEquals(listCheck.toString(), resNotWait.toString());
	}

}
