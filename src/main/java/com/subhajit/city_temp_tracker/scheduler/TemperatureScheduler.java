package com.subhajit.city_temp_tracker.scheduler;

import com.subhajit.city_temp_tracker.model.TemperatureData;
import com.subhajit.city_temp_tracker.repository.TemperatureRepository;
import com.subhajit.city_temp_tracker.service.interfaces.TemperatureDownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class TemperatureScheduler{

    @Autowired
    private TemperatureDownloadService temperatureDownloadService;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Scheduled(fixedRate = 86400000)
    public void scheduleCityTempFetch()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        ZonedDateTime startTimeInIst = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String formattedStartTime = startTimeInIst.format(formatter);
        log.debug("Scheduler trigger starts: {}", formattedStartTime);
        String[] cities = {
                "New York City", "Kolkata", "Berlin", "Mumbai", "Darjeeling", "New Delhi", "London", "Frankfurt",
                "Amsterdam", "Paris", "Warsaw", "Tokyo", "Melbourne", "Beijing", "Bangalore",
                "Los Angeles", "Chicago", "Shanghai", "Hong Kong", "Dubai", "Rome", "Istanbul", "Toronto",
                "Moscow", "Barcelona", "Sydney", "Seoul", "Singapore", "Cairo", "Mexico City", "Bangkok",
                "Rio de Janeiro", "Buenos Aires", "Jakarta", "Lagos", "São Paulo", "Cape Town", "Tehran",
                "Kuala Lumpur", "Riyadh", "Lima", "Vienna", "Athens", "Brussels", "Stockholm", "Zurich",
                "Madrid", "Lisbon", "Helsinki", "Oslo", "Copenhagen", "Dublin", "Edinburgh", "Glasgow",
                "Manchester", "Vancouver", "Montreal", "Calgary", "Brisbane", "Auckland", "Wellington",
                "Christchurch", "Perth", "Adelaide", "Hanoi", "Ho Chi Minh City", "Phnom Penh",
                "Bangui", "Islamabad", "Kathmandu", "Colombo", "Male", "Port Louis", "Abu Dhabi",
                "Doha", "Manama", "Muscat", "Amman", "Jerusalem", "Tel Aviv", "Casablanca", "Algiers",
                "Pune", "Nairobi", "Addis Ababa", "Accra", "Chennai", "Pretoria", "Lucknow", "Harare",
                "Santiago", "Caracas", "Bogotá", "Quito", "La Paz", "Asunción", "Montevideo",
                "San José", "Havana", "San Juan", "Kingston", "Panama City", "Managua"
        };
        for(String city : cities )
        {
            String response = temperatureDownloadService.downloadTemperatureData(city);
            Instant currentInstant = Instant.now();
            ZonedDateTime istDateTime = currentInstant.atZone(ZoneId.of("Asia/Kolkata"));

            TemperatureData temperatureData = new TemperatureData()
                    .setCity(city)
                    .setId(UUID.randomUUID().toString())
                    .setTime(istDateTime.toInstant())
                    .setExtraInfo(response);

            temperatureRepository.save(temperatureData);
            log.debug("city: {} , External API: response{}", city, response);        }
        ZonedDateTime endTimeInIst = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String formattedEndTime = endTimeInIst.format(formatter);

        log.debug("Scheduler trigger ends: {}",formattedEndTime);
    }

}
