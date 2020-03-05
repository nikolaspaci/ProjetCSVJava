package checker;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.univocity.parsers.common.record.Record;

import column.DescriptorColumn;
import column.VerifColumn;
import utils.CsvUtils;
import utils.JsonUtils;

/**
 * Cette classe verifie les données d'un fichier
 */
public class CsvChecker {
	
	private static Logger logger = LogManager.getLogger(CsvChecker.class);
	
	/** liste contenant le header du fichier */
	private List<DescriptorColumn> lHeader;
	
	/** liste contenant les colonnes contenant des règles de vérification */
	private List<VerifColumn> lchecker;

	/**
	 * Instantiates a new csv checker.
	 *
	 * @param pathDesc Chemin vers le fichier décrivant les colonnes du fichier
	 * @param pathCheck Chemin vers le fichier décrivant les règles appliquer à chaque colonne
	 */
	public CsvChecker(String pathDesc,String pathCheck) {
		lchecker=JsonUtils.getCheckingHeader(pathCheck);
		lHeader=JsonUtils.getCsvHeader(pathDesc);
	}

	/**
	 * Fonction en charge de la vérification des enregistrements
	 *
	 * @param lrecord Liste des enregistrements du fichier
	 * @param pathName Le nom du ficher de sorti
	 */
	// Parcours
	public void check(List<Record> lrecord, String pathName) {
		List<Record> listeRecordCheck = lrecord.stream().filter(this::checktype).collect(Collectors.toList());
		CsvUtils.writeCsvRecords(listeRecordCheck, lHeader, pathName);
	}

	/**
	 * Vérifie le type de la donnée et si elle respecte les règles
	 *
	 * @param r l'enregistrement en cours de vérification
	 * @return true, si les données de l'enregistrement respectent les règles
	 */
	public boolean checktype(Record r) {
		try {
			logger.info("Checking of records " + r);
			for (DescriptorColumn c : lHeader) {
				Object value = r.getValue(c.getName(), CsvUtils.getValueClass(c.getDataType()));
				int indexColumn = lchecker.indexOf(c);
				if (indexColumn != -1) {
					for (String rule : lchecker.get(indexColumn).getShould()) {
						
						if (!CheckRules.checkHub(rule, value)) {
							return false;
						}
					}
				}
			}
			logger.info("End of checking of records " + r);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

}
