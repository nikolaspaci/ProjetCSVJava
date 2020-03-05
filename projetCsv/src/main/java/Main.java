import java.util.Scanner;


import anonymizer.CsvAnonymizer;
import checker.CsvChecker;
import utils.CsvUtils;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choix = 0;
		System.out.println("Taper 1 pour v�rifier les donn�es");
		System.out.println("Taper 2 pour anonymiser les donn�es");
		if (sc.hasNextInt())
			choix = sc.nextInt();
		if (choix == 1) {
			System.out.println("Fichier CSV en input :");
			String pathNameCsv=sc.next();
			System.out.println("Fichier d�crivant le CSV :");
			String pathDescCsv=sc.next();
			System.out.println("Fichier d�crivant les donn�es � v�rifier dans le CSV :");
			String pathCheckCsv=sc.next();
			System.out.println("Fichier output CSV :");
			String pathOutputCsv=sc.next();
			CsvChecker checkCsv = new CsvChecker(pathDescCsv,pathCheckCsv);
			checkCsv.check(CsvUtils.getCsvRecords(pathNameCsv),pathOutputCsv);

		} else if(choix == 2){
			System.out.println("Fichier CSV en input :");
			String pathNameCsv=sc.next();
			System.out.println("Fichier d�crivant le CSV :");
			String pathDescCsv=sc.next();
			System.out.println("Fichier d�crivant les donn�es � anonymiser dans le CSV :");
			String pathAnonCsv=sc.next();
			System.out.println("Fichier output CSV :");
			String pathOutputCsv=sc.next();
			CsvAnonymizer anonymCsv = new CsvAnonymizer(pathAnonCsv,pathDescCsv);
			anonymCsv.anonymData(CsvUtils.getCsvRecords(pathNameCsv), pathOutputCsv);
		}
		else {
			System.out.println("saisie incorrecte");
		}
		sc.close();
	}
}
