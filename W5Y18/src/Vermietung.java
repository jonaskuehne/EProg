import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Vermietung {

	public static void main(String[] args) throws FileNotFoundException {
		String dateiName = "vermietung.txt";
		Scanner scanner = new Scanner(new File(dateiName));
		PrintStream output = new PrintStream(System.out);
		
		analyse(scanner, output);
		
		output.close();
		scanner.close();
	}
	
	public static void analyse(Scanner input, PrintStream output) {
		// TODO: Lesen Sie das File von input und geben Sie Ihre Lösung nach output aus.
		
		output.println("Laengste Zeit: " + null);
		output.println("Haeufigste Ausleihe: " + null);
		output.println("Durchschnitt alle: " + null);
		output.println("Durchschnitt nachgefragte Geraete: " + null); 
	}

}
