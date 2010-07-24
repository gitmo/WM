package dbs.project.dev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import dbs.project.entity.Player;
import dbs.project.main.App;

public class Generator {

	
	/**
	 * Example: loadPlayersFromCsvFile("dev/players.csv")
	 * @param String csvFileName
	 * @retun List<Player>
	 */
	public static List<Player> loadSamplePlayersFromCsvFile(String fileName) {
		List<Player> players = new LinkedList<Player>();
		
		try {
			CSVReader reader = getCsvReader(fileName);
			String[] nextLine;
			while((nextLine = reader.readNext()) != null) {
				Date birthday = null;
				Integer height = 0, weight = 0;
				try {
					height = Integer.parseInt(nextLine[3].trim());
					weight = Integer.parseInt(nextLine[4].trim());
					birthday = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(nextLine[2].trim());
				}
				catch(ParseException e) {}
				catch(NumberFormatException e) {}
				
				Player player = new Player(
						nextLine[0].trim(),
						nextLine[1].trim(),
						"",
						birthday,
						"no club",
						height,
						weight
				);
				
				players.add(player);
				
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return players;
	}
	
	private static CSVReader getCsvReader(String fileName) throws FileNotFoundException {
		String filePath = App.class.getClassLoader().getResource(fileName).getPath()
        .replaceAll("%20", " ");
		return new CSVReader(new FileReader(filePath));
	}
	
}
