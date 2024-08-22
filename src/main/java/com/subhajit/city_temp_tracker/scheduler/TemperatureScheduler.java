package com.subhajit.city_temp_tracker.scheduler;

import com.subhajit.city_temp_tracker.model.TemperatureData;
import com.subhajit.city_temp_tracker.repository.TemperatureRepository;
import com.subhajit.city_temp_tracker.service.interfaces.TemperatureDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class TemperatureScheduler{

    @Autowired
    private TemperatureDownloadService temperatureDownloadService;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Scheduled(fixedRate = 3600000)
    public void scheduleCityTempFetch()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        ZonedDateTime startTimeInIst = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String formattedStartTime = startTimeInIst.format(formatter);
        System.out.println("Scheduler trigger starts: "+formattedStartTime);
        String[] cities = {"New York","Kolkata","Berlin"};
        for(String city : cities )
        {
            String response = temperatureDownloadService.downloadTemperatureData(city);
            ZonedDateTime currentTimeInIst = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
            String formattedCurrentTime = currentTimeInIst.format(formatter);
            TemperatureData temperatureData = new TemperatureData()
                    .setCity(city)
                    .setId(UUID.randomUUID().toString())
                    .setTime(formattedCurrentTime.toString())
                    .setExtraInfo(response);

            temperatureRepository.save(temperatureData);
            System.out.println(city+" : "+response);
        }
        ZonedDateTime endTimeInIst = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String formattedEndTime = endTimeInIst.format(formatter);

        System.out.println("Scheduler trigger ends: "+formattedEndTime);
    }

}
