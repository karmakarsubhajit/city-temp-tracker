package com.subhajit.city_temp_tracker.controller;

import com.subhajit.city_temp_tracker.model.ClientRequest;
import com.subhajit.city_temp_tracker.model.ClientResponse;
import com.subhajit.city_temp_tracker.service.interfaces.TemperatureSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/city")
@Slf4j
public class TemperatureController{

    @Autowired
    private TemperatureSearchService temperatureSearchService;

    @GetMapping("/temperature")
    public ResponseEntity<ClientResponse> getTempResponse(@RequestBody ClientRequest clientRequest){
        log.debug("Request received");

        return ResponseEntity.ok(temperatureSearchService.searchTemperatureData(clientRequest));
    }
}
