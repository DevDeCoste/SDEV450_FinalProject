package com.sdev450_finalproject;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.zetcode.util
 * @Date: Apr 6, 2019
 * @Subclass WriteCsvToResponse Description: 
 */
//Imports

//Begin Subclass WriteCsvToResponse
import java.io.PrintWriter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import com.sdev450_finalproject.persistance.Artist.Artist;


public class WriteCsvToResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteCsvToResponse.class);

    public static void writeArtists(PrintWriter writer, List<Artist> artists)  {

        try {

            ColumnPositionMappingStrategy mapStrategy
                    = new ColumnPositionMappingStrategy();

            mapStrategy.setType(Artist.class);
//            mapStrategy.generateHeader();

            String[] columns = new String[]{"id", "name", "popularity"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(artists);

        } catch (CsvException ex) {

            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }

    public static void writeCity(PrintWriter writer, Artist artists) {

        try {

            ColumnPositionMappingStrategy mapStrategy
                    = new ColumnPositionMappingStrategy();

            mapStrategy.setType(Artist.class);

            String[] columns = new String[]{"id", "name", "population"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(artists);

        } catch (CsvException ex) {

            LOGGER.error("Error mapping Bean to CSV", ex);
        }
    }
} //End Subclass WriteCsvToResponse