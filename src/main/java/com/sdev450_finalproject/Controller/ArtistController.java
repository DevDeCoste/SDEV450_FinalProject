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
import com.sdev450_finalproject.persistance.Artist.ArtistRepository;
import java.io.FileNotFoundException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//Begin Subclass ArtistController
@RestController
@RequestMapping("")
public class ArtistController {

    static String FILE_PATH = "./src/main/resources/albumlist.csv";

    @Autowired
    ArtistRepository repository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<ArtistEntity> getAllUsers() {
        // This returns a JSON or XML with the users
        return repository.findAll();
    }

    @GetMapping(path = "/artists")
    public List<ArtistEntity> getEntities() {
        System.out.print(repository);
        return repository.findAll();
    }

    @PostMapping(path = "/artists")
    public boolean createArtist(@RequestBody ArtistEntity artistEntity) {
        repository.save(artistEntity);
        return true;
    }
// @GetMapping("/findAlbumByArtist/{artistName}")
//    public ResponseEntity findAlbumByArtist(@PathVariable String artistName) {
//
//        repository.
//
//        if (Entities.isEmpty()) {
//            //Returns 404 if not present
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//
//        //Returns Album Entity if present
//        return new ResponseEntity(Entities, HttpStatus.OK);
//    }

    public ArrayList<ArtistEntity> findArtist(@PathVariable String artistName)
            throws IOException, ParseException {

        //Opens reader to read CSV file and set table values
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);) {

            String[] nextRecord;
            ArrayList<ArtistEntity> artists = new ArrayList<>();

            while ((nextRecord = csvReader.readNext()) != null) {
                ArtistEntity tempArtist = new ArtistEntity();

                if (nextRecord[2].contains(artistName)) {

                    tempArtist.setId(nextRecord[0]);
                    tempArtist.setArtist(nextRecord[1]);
                    tempArtist.setAlbumName(nextRecord[2]);
                    tempArtist.setGenre(nextRecord[4]);

                    artists.add(tempArtist);

                    csvReader.close();

                }

            }
            search(artistName);
            return artists;
        }

    }

    @GetMapping("/findRandomArtist")
    public ArrayList<ArtistEntity> findRandomArtist() throws IOException {

        //Opens reader to read CSV file and set table values
        String[] nextRecord;
        ArrayList<ArtistEntity> artists = new ArrayList<>();

        if (artists.isEmpty()) {
            Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
            CSVReader csvReader1 = new CSVReader(reader1);

            ArtistEntity tempArtist = new ArtistEntity();

            int i = 0;

            int randInt = new Random().nextInt(100);

            while (i <= randInt) {
                nextRecord = csvReader1.readNext();
                i = i + 2;

                tempArtist.setId(nextRecord[0]);
                tempArtist.setAlbumName(nextRecord[2]);
                tempArtist.setArtist(nextRecord[3]);

                tempArtist.setGenre(nextRecord[4]);
            }
            artists.add(tempArtist);
//				

//                tempArtist.setTracks(nextRecord);
            csvReader1.close();

        }
        return artists;
    }

    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    List<String[]> addNewUser() throws FileNotFoundException, IOException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        List<String[]> myEntries = null;
        String[] nextRecord;
        ArrayList<ArtistEntity> artists = new ArrayList<>();
 try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH), ',')) {
            myEntries = reader.readAll();
            System.out.println(myEntries.size());
        }
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH), ',')) {
            Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
            CSVReader csvReader1 = new CSVReader(reader1);

            ArtistEntity tempArtist = new ArtistEntity();

            int i = 0;
//
//            int randInt = new Random().nextInt(100);
        for (String[] a : myEntries) {
                nextRecord = csvReader1.readNext();
                i = i + 2;

                tempArtist.setId(nextRecord[0]);
                tempArtist.setAlbumName(nextRecord[1]);
                tempArtist.setArtist(nextRecord[2]);

                tempArtist.setGenre(nextRecord[3]);
            }
            artists.add(tempArtist);
        }
        return myEntries;

    }
    
    @GetMapping("/findArtist/{searchArtist}")
    public List<String[]> search(@PathVariable String searchArtist) throws IOException {

        String strSearch = searchArtist;

        List<String[]> myEntries;

        String[] nextRecord;
        ArrayList<ArtistEntity> artists = new ArrayList<>();

        Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
        CSVReader csvReader1 = new CSVReader(reader1);

        ArtistEntity tempArtist = new ArtistEntity();

        int y = 0;

        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH), ',')) {
            myEntries = reader.readAll();
            System.out.println(myEntries.size());
        }

        //Write to existing file
        //CSVWriter writer = new CSVWriter(new FileWriter("test.txt"), ',',CSVWriter.NO_QUOTE_CHARACTER, "\r\n");
        //Iterate through my array to find the row the user input is located on
//        int i = 1;
//        for (String[] line : myEntries) {
//            while (y <= myEntries.size()) {
//                nextRecord = csvReader1.readNext();
//                y = y + 2;
//
//                tempArtist.setId(nextRecord[0]);
//                tempArtist.setAlbumName(nextRecord[2]);
//                tempArtist.setArtist(nextRecord[3]);
//                tempArtist.setGenre(nextRecord[4]);
//                System.out.print(tempArtist);
//                artists.add(tempArtist);
////repository.saveAll(artists);
//
//            }
//            String textLine = Arrays.toString(line).replaceAll("\\[|\\]", "");
//            if (textLine.contains(strSearch)) {
//
//                //Need a method so I can find out what row my item is on
//                System.out.println(textLine);
//                //Code to extract row into something (String array?) so I can edit it's contents
//                //I then need to write the edited row back to it's original row
//
//            } else {
//            }
//            y++;
//        }
//        return artists;
            return myEntries;

    }

    @RequestMapping("/developer/{id}")
    public String developer(@PathVariable Long id, Model model) {
        model.addAttribute(id);
//		model.addAttribute("developer", repository.findOne(id));
//		model.addAttribute("skills", skillRepository.findAll());
        return "developer";
    }
}//End Subclass ArtistController

