package com.intuit.player;

import com.intuit.player.loader.CsvDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayerApplication implements CommandLineRunner {

	@Autowired
	private CsvDataLoaderService csvDataLoaderService;

	public static void main(String[] args) {
		SpringApplication.run(PlayerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		csvDataLoaderService.loadCsvData();
	}
}
