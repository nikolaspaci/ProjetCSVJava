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
 * Cette classe verifie les donn�es d'un fichier
 */
public class CsvChecker {
	
	private static Logger logger = LogManager.getLogger(CsvChecker.class);
	
	/** liste contenant le header du fichier */
	private List<DescriptorColumn> lHeader;
	
	/** liste contenant les colonnes contenant des r�gles de v�rification */
	private List<VerifColumn> lchecker;

	/**
	 * Instantiates a new csv checker.
	 *
	 * @param pathDesc Chemin vers le fichier d�crivant les colonnes du fichier
	 * @param pathCheck Chemin vers le fichier d�crivant les r�gles appliquer � chaque colonne
	 */
	public CsvChecker(String pathDesc,String pathCheck) {
		lchecker=JsonUtils.getCheckingHeader(pathCheck);
		lHeader=JsonUtils.getCsvHeader(pathDesc);
	}

	/**
	 * Fonction en charge de la v�rification des enregistrements
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
	 * V�rifie le type de la donn�e et si elle respecte les r�gles
	 *
	 * @param r l'enregistrement en cours de v�rification
	 * @return true, si les donn�es de l'enregistrement respectent les r�gles
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
