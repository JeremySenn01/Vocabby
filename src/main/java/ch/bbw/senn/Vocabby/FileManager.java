package ch.bbw.senn.Vocabby;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FileManager {

	/**
	 * This method writes the contents of a Set to a .txt-file
	 */
	public static boolean writeSetToFile(Set set) {
		System.out.println("writeSetToFile: " + set.getName());
		String home = System.getProperty("user.home");
		File file = new File(home + "/Downloads/" + set.getName() + "-" + set.getCreationDate() + ".txt");
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));

			writer.write(set.getName());
			writer.newLine();
			writer.write(set.getTheme());
			writer.newLine();
			writer.write(set.getCreationDate().toString());

			writer.newLine();
			String delimiter = ";";
			for (Term term : set.getTerms()) {
				writer.write(term.getOriginal() + delimiter + term.getTranslated());
				writer.newLine();
			}
			writer.close();
			System.out.println("worked");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("failed");

			return false;
		}
	}
	/**
	 * This method imports a txt file and creates a Set from the information
	 * @param file
	 * @param userId
	 * @return
	 */
	//TODO: Finish implementing this method / feature
	public static Set importSetFromTxtFile(File file, UUID userId) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String title = reader.readLine();
			String theme = reader.readLine();

			String row = reader.readLine();
			while (row != null) {
				String term[] = new String[2];
				term = row.split(";");
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
