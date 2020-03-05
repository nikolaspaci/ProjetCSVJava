package anonymizer;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.univocity.parsers.common.record.Record;

import column.AnonymeColumn;
import column.DescriptorColumn;
import utils.CsvUtils;
import utils.JsonUtils;


/**
 * cette classe s'occupe de l'anonymisation des données
 */
public class CsvAnonymizer {
	
	private static Logger logger = LogManager.getLogger(CsvAnonymizer.class);
	
	/** liste contenant le header du fichier */
	private List<DescriptorColumn> lHeader;
	
	public List<DescriptorColumn> getlHeader() {
		return lHeader;
	}

	public List<AnonymeColumn> getlAnonym() {
		return lAnonym;
	}

	/** Liste contenant les colonnes pouvant être anonymisées */
	private List<AnonymeColumn> lAnonym;
	
	/**
	 * Instantiates a new csv anonymizer.
	 *
	 * @param pathAnonCsv Le chemin vers le chemin du fichier décrivant les colonnes à anonymiser 
	 * @param pathDescCsv Le chemin vers le chemin du fichier décrivant les colonnes du fichier 
	 */
	public CsvAnonymizer(String pathAnonCsv,String pathDescCsv) {
		lHeader=JsonUtils.getCsvHeader(pathDescCsv);
		lAnonym=JsonUtils.getAnonymHeader(pathAnonCsv);
	}
	
	/**
	 * Fonction en charge d'anonymiser les données
	 *
	 * @param listRecords Il s'agit de la liste des lignes de données du fichier csv
	 * @param pathName Il s'agit du nom du fichier de sorti
	 */
	public void anonymData(List<Record> listRecords, String pathName) {
		List<String[]> listRowAnonym=new ArrayList<>();
		for (Record r : listRecords) {
			logger.info("Start of anonimization of " + r);
			List<String> listValueAnonym = new ArrayList<>();
			for (AnonymeColumn columnA : lAnonym) {
				int ind = lHeader.indexOf(columnA);
				if (ind != -1) {
					Object valueAnonym = r.getValue(columnA.getName(), CsvUtils.getValueClass(lHeader.get(ind).getDataType()));
					listValueAnonym.add(AnonymizeRules.anonymiseHub(columnA.getChangeTo(), valueAnonym).toString());
				}
				logger.info("End of anonimization of " + r);
			}
			listRowAnonym.add(listValueAnonym.toArray(new String[0]));
		}
		logger.info("The anonymization went well");
		logger.info("Rewriting csv file");
		CsvUtils.writeCsvRows(listRowAnonym,lAnonym,pathName);
		logger.info("Rewriting went well");
	}
}
