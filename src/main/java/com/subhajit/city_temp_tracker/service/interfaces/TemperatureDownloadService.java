package com.subhajit.city_temp_tracker.service.interfaces;


import org.springframework.stereotype.Service;

@Service
public interface TemperatureDownloadService{
     public String downloadTemperatureData(String city);
}