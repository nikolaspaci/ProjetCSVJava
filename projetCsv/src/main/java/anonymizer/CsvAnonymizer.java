package anonymizer;

import java.io.IOException;
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
	 * @param lr Il s'agit de la liste des lignes de données du fichier csv
	 * @param pathName Il s'agit du nom du fichier de sorti
	 */
	public void anonymData(List<Record> lr, String pathName) {
		List<String[]> listrow=new ArrayList<>();
		for (Record r : lr) {
			logger.info("Start of anonimization of " + r);
			List<String> la = new ArrayList<>();
			for (AnonymeColumn c : lAnonym) {
				int ind = lHeader.indexOf(c);
				if (ind != -1) {
					Object b = r.getValue(c.getName(), CsvUtils.getValueClass(lHeader.get(ind).getDataType()));
					la.add(AnonymizeRules.anonymiseHub(c.getChangeTo(), b).toString());
				}
				logger.info("End of anonimization of " + r);
			}
			listrow.add(la.toArray(new String[0]));
		}
		logger.info("The anonymization went well");
		logger.info("Rewriting csv file");
		CsvUtils.writeCsvRows(listrow,lAnonym,pathName);
		logger.info("Rewriting went well");
	}
}
