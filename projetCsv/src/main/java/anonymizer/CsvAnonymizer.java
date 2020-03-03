package anonymizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.univocity.parsers.common.record.Record;

import column.AnonymeColumn;
import column.DescriptorColumn;
import utils.CsvUtils;
import utils.JsonUtils;


/**
 * cette classe s'occupe de l'anonymisation des donn�es
 */
public class CsvAnonymizer {
	
	/** liste contenant le header du fichier */
	private List<DescriptorColumn> lHeader;
	
	public List<DescriptorColumn> getlHeader() {
		return lHeader;
	}

	public List<AnonymeColumn> getlAnonym() {
		return lAnonym;
	}

	/** Liste contenant les colonnes pouvant �tre anonymis�es */
	private List<AnonymeColumn> lAnonym;
	
	/**
	 * Instantiates a new csv anonymizer.
	 *
	 * @param pathAnonCsv Le chemin vers le chemin du fichier d�crivant les colonnes � anonymiser 
	 * @param pathDescCsv Le chemin vers le chemin du fichier d�crivant les colonnes du fichier 
	 */
	public CsvAnonymizer(String pathAnonCsv,String pathDescCsv) {
		lHeader=JsonUtils.getCsvHeader(pathDescCsv);
		lAnonym=JsonUtils.getAnonymHeader(pathAnonCsv);
	}
	
	/**
	 * Fonction en charge d'anonymiser les donn�es
	 *
	 * @param lr Il s'agit de la liste des lignes de donn�es du fichier csv
	 * @param pathName Il s'agit du nom du fichier de sorti
	 */
	public void anonymData(List<Record> lr, String pathName) {
		List<String[]> listrow=new ArrayList<>();
		for (Record r : lr) {
			List<String> la = new ArrayList<>();
			for (AnonymeColumn c : lAnonym) {
				int ind = lHeader.indexOf(c);
				if (ind != -1) {
					Object b = r.getValue(c.getName(), CsvUtils.convert(lHeader.get(ind).getDataType()));
					la.add(AnonymizeRules.anonymiseHub(c.getChangeTo(), b).toString());
				}
			}
			listrow.add(la.toArray(new String[0]));
		}
		CsvUtils.writeCsvRows(listrow,lAnonym,pathName);
	}
}