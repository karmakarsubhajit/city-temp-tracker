package com.subhajit.city_temp_tracker.service.interfaces;


import com.subhajit.city_temp_tracker.model.ClientRequest;
import com.subhajit.city_temp_tracker.model.ClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface TemperatureSearchService{
    public ClientResponse searchTemperatureData(ClientRequest clientRequest);
}