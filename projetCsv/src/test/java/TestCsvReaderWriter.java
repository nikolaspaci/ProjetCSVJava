

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.univocity.parsers.common.record.Record;

import column.AnonymeColumn;
import column.DescriptorColumn;
import utils.CsvUtils;

public class TestCsvReaderWriter {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void testReaderCsv() throws IOException {
		String pathExemple = getClass().getClassLoader().getResource("exemple.csv").getFile();
		List<Record> listRecordCsv = CsvUtils.getCsvRecords(pathExemple);
		assertTrue(listRecordCsv.size() != 0);
	}

	@Test
	public void testWriterRecordsCsv() throws IOException {
		final File tempFile = tempFolder.newFile("testWriter.csv");
		String pathExemple = getClass().getClassLoader().getResource("exemple.csv").getFile();
		String outputAnoCsv = tempFile.getPath();
		List<Record> listRecordCsvTempFile = CsvUtils.getCsvRecords(outputAnoCsv);
		assertTrue(listRecordCsvTempFile.size() == 0);

		List<Record> listRecordCsv = CsvUtils.getCsvRecords(pathExemple);
		List<DescriptorColumn> listDescriptor = new ArrayList<>();
		listDescriptor.add(new DescriptorColumn("STRING", "Nom"));
		listDescriptor.add(new DescriptorColumn("INT", "Age"));
		listDescriptor.add(new DescriptorColumn("DOUBLE", "Date"));
		listDescriptor.add(new DescriptorColumn("STRING", "EMAIL_PRO"));
		listDescriptor.add(new DescriptorColumn("STRING", "EMAIL_PERSO"));
		CsvUtils.writeCsvRecords(listRecordCsv, listDescriptor, outputAnoCsv);
		listRecordCsvTempFile = CsvUtils.getCsvRecords(outputAnoCsv);
		assertTrue(listRecordCsvTempFile.size() != 0);
	}

	@Test
	public void testWriterStringCsv() throws IOException {
		final File tempFile = tempFolder.newFile("testWriter.csv");
		String outputAnoCsv = tempFile.getPath();
		List<Record> listRecordCsvTempFile = CsvUtils.getCsvRecords(outputAnoCsv);
		assertTrue(listRecordCsvTempFile.size() == 0);
		
		List<String[]> listRow = new ArrayList<>();
		List<DescriptorColumn> listDC = new ArrayList<>();
		createSampleRowAndColumnDescriptor(listRow, listDC);
		
		CsvUtils.writeCsvRows(listRow, listDC, outputAnoCsv);
		listRecordCsvTempFile = CsvUtils.getCsvRecords(outputAnoCsv);
		assertTrue(listRecordCsvTempFile.size() == 3);
	}
	
	private void createSampleRowAndColumnDescriptor(List<String[]> listRow,List<DescriptorColumn> listDC) {
		String[] row1= {"Tom","Moore","Montgeron","BRED"};
		String[] row2= {"Nikola","Spaci","Clichy","Societe Generale"};
		String[] row3= {"Marcel Junior","Loutarila","Evry","Def Jam France"};
		listRow.add(row1);
		listRow.add(row2);
		listRow.add(row3);
		listDC.add(new DescriptorColumn("STRING","Prenom"));
		listDC.add(new DescriptorColumn("STRING","Nom"));
		listDC.add(new DescriptorColumn("STRING","Ville"));
		listDC.add(new DescriptorColumn("STRING","Entreprise"));		
	}
}
