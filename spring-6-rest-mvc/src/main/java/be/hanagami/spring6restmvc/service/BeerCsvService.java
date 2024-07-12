package be.hanagami.spring6restmvc.service;

import be.hanagami.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {
    List<BeerCSVRecord> covertCSV(File csvFile);
}
