package be.hanagami.spring6restmvc.service;

import be.hanagami.spring6restmvc.model.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeerCsvServiceTest {

    BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void covertCSV() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

        List<BeerCSVRecord> resc = beerCsvService.covertCSV(file);

        System.out.println(resc.size());

        assertThat(resc.size()).isGreaterThan(0);
    }
}