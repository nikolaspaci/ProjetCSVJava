package utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


import column.AnonymeColumn;
import column.DescriptorColumn;
import column.VerifColumn;

/**
 * Fonction utile pour lire un fichier json
 */
public class JsonUtils {
	
	private static Logger logger = LogManager.getLogger(JsonUtils.class);
	
	/**
	 * Instantiates a new json utils.
	 */
	private JsonUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Retourne les colonnes d'un fichier CSV
	 *
	 * @param pathName le fichier d�crivant les colonnes du CSV
	 * @return Une liste de colonne (nom,type)
	 */
	public static List<DescriptorColumn> getCsvHeader(String pathName) {
		logger.info("Trying to get column of " + pathName);
		Gson gson = new Gson();
		Type listType = new TypeToken<List<DescriptorColumn>>() {
			/**/}.getType();
		try (JsonReader reader = new JsonReader(new FileReader(pathName))) {
			return gson.fromJson(reader, listType);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}

	/**
	 Retourne les colonnes associ�s � des r�gles de v�rification
	 *
	 * @param pathName le fichier d�crivant les colonnes � v�rifier, et les v�rifications � faire
	 * @return Une liste de colonne (nom,r�gles v�rifications)
	 */
	public static List<VerifColumn> getCheckingHeader(String pathName) {
		logger.info("Trying to get specific column of file " + pathName + " associated with specific checking rules");
		Gson gson = new Gson();
		Type listType = new TypeToken<List<VerifColumn>>() {}.getType();
		try (JsonReader reader = new JsonReader(new FileReader(pathName))) {

			return gson.fromJson(reader, listType);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 *	 Retourne les colonnes associ�es � des r�gles d'anonymisation
	 *
	 * @param le fichier d�crivant les colonnes � anonymiser, et les anonymisations � faire
	 * @return Une liste de colonne (nom,r�gles anonymisations)
	 */
	public static List<AnonymeColumn> getAnonymHeader(String pathName) {
		logger.info("Trying to get specific column of file " + pathName + " associated with specific anonimization rules");
		Gson gson = new Gson();
		Type listType = new TypeToken<List<AnonymeColumn>>() {}.getType();
		try (JsonReader reader = new JsonReader(new FileReader(pathName))){
			return gson.fromJson(reader, listType);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}

}
