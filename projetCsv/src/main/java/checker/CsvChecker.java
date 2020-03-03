package checker;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.univocity.parsers.common.record.Record;

import column.DescriptorColumn;
import column.VerifColumn;
import utils.CsvUtils;
import utils.JsonUtils;

/**
 * Cette classe verifie les donn�es d'un fichier
 */
public class CsvChecker {
	
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
			for (DescriptorColumn c : lHeader) {
				Object a = r.getValue(c.getName(), CsvUtils.convert(c.getDataType()));
				int ind = lchecker.indexOf(c);
				if (ind != -1) {
					for (String s : lchecker.get(ind).getShould()) {
						if (!CheckRules.checkHub(s, a)) {
							return false;
						}
					}
				}
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

}
