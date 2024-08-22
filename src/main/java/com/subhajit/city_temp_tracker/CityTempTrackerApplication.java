package com.subhajit.city_temp_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.subhajit.city_temp_tracker")
@EnableScheduling
@EnableElasticsearchRepositories
public class CityTempTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityTempTrackerApplication.class, args);
	}

}
