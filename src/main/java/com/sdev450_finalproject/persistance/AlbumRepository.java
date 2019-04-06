package com.sdev450_finalproject.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.Arrays;

@Transactional
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

    Page<AlbumEntity> findAll(Pageable pageable);

    // https://api.spotify.com/v1/artists/The Eagles


//    public List<String[]> readAll(Reader reader) throws Exception {
//        CSVReader csvReader = new CSVReader(reader);
//        List<String[]> list = new ArrayList<>();
//        list = csvReader.readAll();
//        reader.close();
//        csvReader.close();
//        return list;
//    }
//
//    public String readAllExample() throws Exception {
//        Reader reader = Files.newBufferedReader(
//                ClassLoader.getSystemResource("albumlist.csv").toURI());
//        return CsvReaderExamples.readAll(reader).toString();
//    }
//
//    CSVReader reader = new CSVReader(new FileReader("albumlist.csv"), ',' , '"' , 1);
//    String[] nextLine;
//
//            while((nextLine = reader.readNext()) != null) {
//        if (nextLine != null) {
//            //Verifying the read data here
//            System.out.println(Arrays.toString(nextLine));
//        }
//    }

}