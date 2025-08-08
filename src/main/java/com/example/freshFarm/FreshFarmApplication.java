package com.example.freshFarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.example.freshFarm.service.DataLoaderService;

@SpringBootApplication
public class FreshFarmApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreshFarmApplication.class, args);
	}
}


//public class FreshFarmApplication implements CommandLineRunner {
//	private final DataLoaderService dataLoaderService;
//
//	@Autowired
//	public FreshFarmApplication(DataLoaderService dataLoaderService) {
//		this.dataLoaderService = dataLoaderService;
//	}
//	public static void main(String[] args) {
//		SpringApplication.run(FreshFarmApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		String filePath = "src/main/resources/data/customer_final.csv";
//		dataLoaderService.loadAndTransform(filePath);
//	}
//
//}


