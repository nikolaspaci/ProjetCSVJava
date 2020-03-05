package utils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import column.Column;
import column.DescriptorColumn;

/**
 * Fonction utile pour lire et écrire un fichier CSV
 */
public class CsvUtils {
	static CsvWriterSettings csvWriteSetting=new CsvWriterSettings();
	static CsvParserSettings csvReadSetting=new CsvParserSettings();
	static {
		csvReadSetting.getFormat().setDelimiter(';');
		csvReadSetting.setHeaderExtractionEnabled(true);
		csvReadSetting.getFormat().setLineSeparator("\n");
		csvWriteSetting.getFormat().setDelimiter(";");
		csvWriteSetting.getFormat().setLineSeparator("\n");
	}
	
	
	private static Logger logger = LogManager.getLogger(CsvUtils.class);
	
	/**
	 * Instantiates a new csv utils.
	 */
	private CsvUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Récupere les enregistrements d'un fichier
	 *
	 * @param pathName le chemin du fichier CSV
	 * @return une liste avec chaque enregistrement
	 */
	public static List<Record> getCsvRecords(String pathName) {
		logger.info("Opening the csv file " + pathName + " in order to read it");
		File fileToRead = new File(pathName);
		CsvParser parser = new CsvParser(csvReadSetting);
		logger.info("Reading  of file " + pathName + " went well");
		return parser.parseAllRecords(fileToRead);
	}

	/**
	 * Ecrire des enregistrements(Record) dans un CSV
	 *
	 * @param listRecords Liste d'enregistrements
	 * @param lHeader Liste contenant le nom des colonnes du CSV
	 * @param outputPathFile chemin du fichier de sorti
	 */
	public static void writeCsvRecords(List<Record> listRecords, List<DescriptorColumn> lHeader, String outputPathFile) {																// chosit
		logger.info("Trying to write records into " + outputPathFile);
		csvWriteSetting.setHeaders(getHeader(lHeader));
		CsvWriter csvWriter = new CsvWriter(new File(outputPathFile), csvWriteSetting);
		csvWriter.writeHeaders();
		csvWriter.writeRecords(listRecords);
		csvWriter.close();
		logger.info("Wrting of records into " + outputPathFile + " went well");		
	}
	
	/**
	 * Ecrire des enregistrements(au format String) dans un CSV
	 *
	 * @param listRecords Liste d'enregistrements
	 * @param lHeader Liste contenant le nom des colonnes du CSV
	 * @param outputPathFile chemin du fichier de sorti
	 */
	public static void writeCsvRows(List<String[]> listRecords, List<? extends Column> lHeader, String outputPathFile) { 
		logger.info("Trying to write String into " + outputPathFile);
		csvWriteSetting.setHeaders(getHeader(lHeader));
		CsvWriter csvWriter = new CsvWriter(new File(outputPathFile),csvWriteSetting);
		csvWriter.writeHeaders();
		for (String[] row : listRecords) {
			csvWriter.writeRow(row);
		}
		csvWriter.close();
		logger.info("Wrting of String into " + outputPathFile + " went well");
	}

	public static String[] getHeader(List<? extends Column>listColumns) {
		List<String> lNameColumn=new ArrayList<>();
		for (Column c: listColumns) {
			lNameColumn.add(c.getName());
		}
		return lNameColumn.toArray(new String[0]);
		
	}
	/**
	 * fonction retourne classe du type indiqué
	 *
	 * @param dataType le type
	 * @return the class<? extends object>
	 */
	public static Class<? extends Object> getValueClass(String dataType) {
		switch (dataType) {
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
