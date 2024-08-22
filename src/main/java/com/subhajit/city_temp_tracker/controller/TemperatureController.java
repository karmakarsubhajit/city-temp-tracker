package com.subhajit.city_temp_tracker.controller;

import com.subhajit.city_temp_tracker.model.ClientRequest;
import com.subhajit.city_temp_tracker.model.ClientResponse;
import com.subhajit.city_temp_tracker.service.interfaces.TemperatureSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/city")
public class TemperatureController{

    @Autowired
    private TemperatureSearchService temperatureSearchService;

    @GetMapping("/temperature")
    public ResponseEntity<ClientResponse> getTempResponse(@RequestBody ClientRequest clientRequest){
        System.out.println("Request received");

        return ResponseEntity.ok(temperatureSearchService.searchTemperatureData(clientRequest));
    }
}
