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
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.Artist.ArtistEntity;
import com.sdev450_finalproject.persistance.Artist.ArtistRepository;
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
import org.springframework.web.bind.annotation.RestController;

//Begin Subclass ArtistController
@RestController
@RequestMapping("")
public class ArtistController {

    static String FILE_PATH = "./src/main/resources/master.csv";

    @Autowired
    ArtistRepository repository;

    @GetMapping(path = "/artists")
    public List<ArtistEntity> getEntities() {
        return repository.findAll();
    }

    @PostMapping(path = "/artists")
    public boolean createArtist(@RequestBody ArtistEntity artistEntity) {
        //findAlbum(@PathVariable(albumLists));
        repository.save(artistEntity);
        return true;
    }

//    @GetMapping(path="/artists/test")
//    public List<ArtistEntity> getEntity(){
//        return repository.saveAll(artistEntity);
//        
//    }
    
    
    
    @GetMapping("/findArtists/{findOne}")
    public ArrayList<ArtistEntity> findOne(@PathVariable("findOne") String artName) {
        
        return repository.findByArtistEquals("findOne");
    }

//    @GetMapping("/findArtist/{artistName}")
//    public ResponseEntity findArtist(@PathVariable("artistName") String artistName) {
//
//        ArrayList<ArtistEntity> Entities = repository.findAllByArtist(artistName);
//        if (Entities.isEmpty()) {
//            //Returns 404 if not present
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//
//        //Returns Album Entity if present
//        return new ResponseEntity(Entities, HttpStatus.OK);
//    }

    
    @GetMapping(path = "/findArtist/TheBeatles")
    public ArrayList<ArtistEntity> findArtist(@PathVariable("TheBeatles") String searchArtist) 
            throws IOException{

        //Opens reader to read CSV file and set table values
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);) {

            String[] nextRecord;
            
            ArrayList<ArtistEntity> artistLists = new ArrayList<>();
            
System.out.print(artistLists.toString());


            while ((nextRecord = csvReader.readNext()) != null) {
                ArtistEntity tempArtist = new ArtistEntity();

                if (nextRecord[2].contains(searchArtist)) {

                    tempArtist.setArtist(nextRecord[1]);
                    tempArtist.setAlbumName(nextRecord[2]);
                    tempArtist.setGenre(nextRecord[4]);

                    artistLists.add(tempArtist);
              
                    if (repository.findByArtistEquals(tempArtist.getArtist()) 
                            == null) {
                        repository.save(tempArtist);
                    }
                }

            }

            csvReader.close();

            if (artistLists.isEmpty()) {
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
                tempArtist.setArtist(nextRecord[2]);
                tempArtist.setAlbumName(nextRecord[3]);
                tempArtist.setGenre(nextRecord[4]);

                artistLists.add(tempArtist);
                repository.save(tempArtist);
                csvReader1.close();

            }
            
            return artistLists;
        }
    }
}//End Subclass ArtistController

