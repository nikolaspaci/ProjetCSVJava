package projetCsv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public class CsvReader {
public static void main(String[] args) {
	File fileToRead=new File(CsvReader.class.getClassLoader().getResource("exemple.csv").getFile());
	CsvParserSettings settings = new CsvParserSettings();
	settings.getFormat().setDelimiter (';');
	settings.setHeaderExtractionEnabled(true);
	settings.getFormat().setLineSeparator("\n");
	CsvParser parser = new CsvParser(settings);
	String[] CsvHeader;

	for(Record record : parser.iterateRecords(fileToRead)) {
		for(String header: record.getMetaData().headers()){
			System.out.print(header+" : "+record.getString(header)+" ");
		}
		System.out.println();
	}

}
}
