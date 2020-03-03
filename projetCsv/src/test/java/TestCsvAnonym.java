

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.univocity.parsers.common.record.Record;

import anonymizer.CsvAnonymizer;
import checker.CsvChecker;
import column.AnonymeColumn;
import utils.CsvUtils;

public class TestCsvAnonym {
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	@Test
	public void testAnonymizeCsv() throws IOException {
		final File tempFile = tempFolder.newFile("testAnon.csv");
		String pathExemple = getClass().getClassLoader().getResource("exemple.csv").getFile();
		String pathAnonymRules = getClass().getClassLoader().getResource("AnonymeCsv.json").getFile();
		String pathDescCsv = getClass().getClassLoader().getResource("DescriptorCsv.json").getFile();
		String outputAnoCsv = tempFile.getPath();
		List<Record> listRecordCsv = CsvUtils.getCsvRecords(pathExemple);
		CsvAnonymizer csvAnon = new CsvAnonymizer(pathAnonymRules, pathDescCsv);
		csvAnon.anonymData(listRecordCsv, outputAnoCsv);
		List<Record> listAnon = CsvUtils.getCsvRecords(outputAnoCsv);
		for (int i=0;i<listRecordCsv.size();i++) {
				for (AnonymeColumn c : csvAnon.getlAnonym()) {
					String varNotAnon = listRecordCsv.get(i).getString(c.getName());
					String varAnon = listAnon.get(i).getString(c.getName());
					System.out.println(c.getName()+" non anonymisée: "+varNotAnon);
					System.out.println(c.getName()+" anonymisée: "+varAnon);
					assertTrue(varNotAnon!=varAnon);
				}
			
		}
	}

}
