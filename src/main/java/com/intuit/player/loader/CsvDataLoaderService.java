package com.intuit.player.loader;

import com.intuit.player.constant.RuntimeConstants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class CsvDataLoaderService {

    @Autowired
    private List<ICsvDBLoader> loaders;
    @Autowired
    private ResourceLoader resourceLoader;

    public void loadCsvData() {
        for (var loader: loaders) {
            readAndLoad(loader);
        }
    }

    private void readAndLoad(ICsvDBLoader loader) {
        try (InputStream inputStream = resourceLoader.getResource(loader.getCSVPath()).getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> rows = csvReader.readAll();
            ExecutorService exec = Executors.newFixedThreadPool(RuntimeConstants.CSV_LOADER_MAX_DEGREE_OF_PARALLELISM);
            List<Callable<Boolean>> tasks = new ArrayList<>();
            for (int i = loader.getCSVContentRowStartIndex(); i < rows.size(); i++) {
                var row = rows.get(i);
                tasks.add(() -> loader.load(row));
            }

            long startTime = System.currentTimeMillis();
            List<Future<Boolean>> loadResults = exec.invokeAll(tasks);
            long endTime = System.currentTimeMillis();
            long successfulSaves = loadResults.stream().filter(CsvDataLoaderService::isSuccessfulLoad).count();

            log.info("\n\n"+loader.getCSVDBName()+" - loaded "+successfulSaves+"/"+rows.size()+" entities in "+(endTime - startTime)+"[ms]\n");
        } catch (IOException | CsvException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static boolean isSuccessfulLoad(Future<Boolean> rowLoadRunResult) {
        try {
            return rowLoadRunResult.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
