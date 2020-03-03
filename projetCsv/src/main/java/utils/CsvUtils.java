package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import column.Column;
import column.DescriptorColumn;

/**
 * Fonction utile pour lire et �crire un fichier CSV
 */
public class CsvUtils {

	/**
	 * Instantiates a new csv utils.
	 */
	private CsvUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * R�cupere les enregistrements d'un fichier
	 *
	 * @param pathName le chemin du fichier CSV
	 * @return une liste avec chaque enregistrement
	 */
	public static List<Record> getCsvRecords(String pathName) {
		File fileToRead = new File(pathName);
		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setDelimiter(';');
		settings.setHeaderExtractionEnabled(true);
		settings.getFormat().setLineSeparator("\n");
		CsvParser parser = new CsvParser(settings);
		return parser.parseAllRecords(fileToRead);
	}

	/**
	 * Ecrire des enregistrements(Record) dans un CSV
	 *
	 * @param lr Liste d'enregistrements
	 * @param lHeader Liste contenant le nom des colonnes du CSV
	 * @param pathName chemin du fichier de sorti
	 */
	public static void writeCsvRecords(List<Record> lr, List<DescriptorColumn> lHeader, String pathName) {																// chosit
		CsvWriterSettings csvSet=new CsvWriterSettings();
		csvSet.getFormat().setDelimiter(";");
		csvSet.getFormat().setLineSeparator("\n");
		csvSet.setHeaders(getHeader(lHeader));
		CsvWriter csvWriter = new CsvWriter(new File(pathName), csvSet);
		csvWriter.writeHeaders();
		csvWriter.writeRecords(lr);
		csvWriter.close();
	}
	
	/**
	 * Ecrire des enregistrements(au format String) dans un CSV
	 *
	 * @param lr Liste d'enregistrements
	 * @param lHeader Liste contenant le nom des colonnes du CSV
	 * @param pathName chemin du fichier de sorti
	 */
	public static void writeCsvRows(List<String[]> lr, List<? extends Column> lHeader, String pathName) { 
		CsvWriterSettings csvSet=new CsvWriterSettings();
		csvSet.getFormat().setDelimiter(";");
		csvSet.getFormat().setLineSeparator("\n");
		csvSet.setHeaders(getHeader(lHeader));
		CsvWriter csvWriter = new CsvWriter(new File(pathName),csvSet);
		csvWriter.writeHeaders();
		for (String[] row : lr) {
			csvWriter.writeRow(row);
		}
		csvWriter.close();
	}

	public static String[] getHeader(List<? extends Column>lc) {
		List<String> lNameColumn=new ArrayList<>();
		for (Column c: lc) {
			lNameColumn.add(c.getName());
		}
		return lNameColumn.toArray(new String[0]);
		
	}
	/**
	 * fonction retourne classe du type indiqu�
	 *
	 * @param a le type
	 * @return the class<? extends object>
	 */
	public static Class<? extends Object> convert(String a) {
		switch (a) {
		case "STRING":
			return String.class;
		case "DOUBLE":
			return Double.class;
		case "INT":
			return Integer.class;
		default:
			break;
		}
		return null;
	}

}
