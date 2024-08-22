package com.subhajit.city_temp_tracker.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhajit.city_temp_tracker.model.ClientRequest;
import com.subhajit.city_temp_tracker.model.ClientResponse;
import com.subhajit.city_temp_tracker.model.TemperatureData;
import com.subhajit.city_temp_tracker.repository.TemperatureRepository;
import com.subhajit.city_temp_tracker.service.interfaces.TemperatureSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TemperatureSearchServiceImpl implements TemperatureSearchService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public ClientResponse searchTemperatureData(ClientRequest clientRequest)
    {
        ClientResponse clientResponse =  new ClientResponse();
        List<Map<String,Object>> data = new ArrayList<>();
        System.out.println("searchTemperatureData method starts");
        if(clientRequest.getData() != null ){
            for(String city :  clientRequest.getData())
            {
                System.out.println("Printing city: "+city);
                List<TemperatureData> responseList = temperatureRepository.findByCity(city);
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
                System.out.println("Debug2");
                System.out.println("response: "+response);
                Map<String,Object> cityRespObj = new HashMap<>();
                cityRespObj.put("city",city);
                JsonNode extraInfoJson = null;
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    extraInfoJson = objectMapper.readTree(response.getExtraInfo());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                cityRespObj.put("temperatureInC",extraInfoJson.get("current").get("temp_c"));
                cityRespObj.put("temperatureInF",extraInfoJson.get("current").get("temp_f"));
                cityRespObj.put("extraInfo",extraInfoJson);
                cityRespObj.put("recordId",response.getId());
                cityRespObj.put("dataSyncTimestamp",response.getTime());
                data.add(cityRespObj);
            }
        }
        clientResponse.setData(data);
        return clientResponse;
    }
}
