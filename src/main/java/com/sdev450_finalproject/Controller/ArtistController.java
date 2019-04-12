package com.sdev450_finalproject.Controller;

/**
 * @Course: SDEV 450 - Enterprise Java
 * @Author Name: Madeline Merced
 * @Assignment Name: com.sdev450_finalproject.Controller
 * @Date: Apr 6, 2019
 * @Subclass ArtistController Description:
 */
//Imports
import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Artist.ArtistEntity;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Begin Subclass ArtistController
@RestController
@RequestMapping("")
public class ArtistController {

    static String FILE_PATH = "./src/main/resources/albumlist.csv";

    @GetMapping("/findArtist/{artistName}")
    public ArrayList<ArtistEntity> findArtist(@PathVariable("artistName") String searchArtist)
            throws IOException, ParseException {

        searchArtist = "artistName";

        //Opens reader to read CSV file and set table values
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);) {

            String[] nextRecord;
            ArrayList<ArtistEntity> artists = new ArrayList<>();

            while ((nextRecord = csvReader.readNext()) != null) {
                ArtistEntity tempArtist = new ArtistEntity();

                if (nextRecord[nextRecord.length - 2].contains("artistName")) {

                    tempArtist.setId(nextRecord[0]);
                    tempArtist.setArtistName(nextRecord[3]);
                    tempArtist.setAlbumName(nextRecord[2]);
//                    tempArtist.setTracks(nextRecord);
                    tempArtist.setGenre(nextRecord[4]);

                    artists.add(tempArtist);

                }

            }

            csvReader.close();

            if (artists.isEmpty()) {
                Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
                CSVReader csvReader1 = new CSVReader(reader1);

                ArtistEntity tempArtist = new ArtistEntity();

                int i = 0;

                int randInt = new Random().nextInt(100);

                while (i <= randInt) {
                    nextRecord = csvReader1.readNext();
                    i = i + 2;
                }

//				
                System.out.println(Arrays.toString(nextRecord));
                tempArtist.setId(nextRecord[0]);
                tempArtist.setArtistName(nextRecord[3]);
                tempArtist.setAlbumName(nextRecord[2]);
//                tempArtist.setTracks(nextRecord);
                tempArtist.setGenre(nextRecord[4]);

                artists.add(tempArtist);
                csvReader1.close();

            }
            search(searchArtist);
            return artists;
        }
    }

    public static void search(String searchArtist) {
        try {

            String strSearch = "Nirvana";

            List<String[]> myEntries;
            try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH), ',')) {
                myEntries = reader.readAll();
                System.out.println(myEntries.size());
            }

            //Write to existing file
            //CSVWriter writer = new CSVWriter(new FileWriter("test.txt"), ',',CSVWriter.NO_QUOTE_CHARACTER, "\r\n");
            //Iterate through my array to find the row the user input is located on
            int i = 1;
            for (String[] line : myEntries) {

                String textLine = Arrays.toString(line).replaceAll("\\[|\\]", "");
                System.out.println(textLine);
                if (textLine.contains(strSearch)) {
                    //Need a method so I can find out what row my item is on
                    System.out.println("Found - Your item is on row: ...:" + i);

                    //Code to extract row into something (String array?) so I can edit it's contents
                    //I then need to write the edited row back to it's original row
                } else {
                    System.out.println("Not found");
                }
                i++;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    	@RequestMapping("/developer/{id}")
	public String developer(@PathVariable Long id, Model model) {
            model.addAttribute(id);
//		model.addAttribute("developer", repository.findOne(id));
//		model.addAttribute("skills", skillRepository.findAll());
		return "developer";
	}
}//End Subclass ArtistController

