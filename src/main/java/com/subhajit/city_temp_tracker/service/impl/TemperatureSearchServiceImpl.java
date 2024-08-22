package com.subhajit.city_temp_tracker.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhajit.city_temp_tracker.model.ClientRequest;
import com.subhajit.city_temp_tracker.model.ClientResponse;
import com.subhajit.city_temp_tracker.model.TemperatureData;
import com.subhajit.city_temp_tracker.repository.TemperatureRepository;
import com.subhajit.city_temp_tracker.service.interfaces.TemperatureSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TemperatureSearchServiceImpl implements TemperatureSearchService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public ClientResponse searchTemperatureData(ClientRequest clientRequest)
    {
        ClientResponse clientResponse =  new ClientResponse();
        List<Map<String,Object>> data = new ArrayList<>();
        log.debug("searchTemperatureData method starts");
        if(clientRequest.getData() != null ){
            for(String city :  clientRequest.getData())
            {
                log.debug("Printing city: {}",city);
                Sort sort = Sort.by(Sort.Direction.DESC, "time");
                List<TemperatureData> responseList = temperatureRepository.findByCity(city,sort);
                TemperatureData response = null;
                if(responseList.size()>0)
                    response = responseList.get(0);
                else
                {
                    data.add(Map.of(
                            "city", city,
                            "error", "Sorry! This city not available!"
                    ));
                    continue;
                }
                log.debug("ES query response: {}",response);
                Map<String,Object> cityRespObj = new HashMap<>();
                cityRespObj.put("city",city);
                JsonNode extraInfoJson = null;
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    extraInfoJson = objectMapper.readTree(response.getExtraInfo());
                } catch (JsonProcessingException e) {
                    extraInfoJson = null;
                }
                cityRespObj.put("temperatureInC",extraInfoJson.get("current").get("temp_c"));
                cityRespObj.put("temperatureInF",extraInfoJson.get("current").get("temp_f"));
                cityRespObj.put("extraInfo",extraInfoJson);
                cityRespObj.put("recordId",response.getId());
                Instant dataSyncTimestamp = response.getTime();
                ZonedDateTime istDateTime = dataSyncTimestamp.atZone(ZoneId.of("Asia/Kolkata"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDataSyncTimestamp = istDateTime.format(formatter);

                cityRespObj.put("dataSyncTimestamp", formattedDataSyncTimestamp);
                data.add(cityRespObj);
            }
        }
        clientResponse.setData(data);
        return clientResponse;
    }
}
