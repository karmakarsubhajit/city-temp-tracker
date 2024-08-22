package com.subhajit.city_temp_tracker.service.impl;


import com.subhajit.city_temp_tracker.service.interfaces.TemperatureDownloadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TemperatureDownloadServiceImpl implements TemperatureDownloadService {

    @Value("${temp.service.api.baseurl}")
    private String baseUrl;

    @Value("${temp.service.api.key}")
    private String apiKey;


    @Override
    public String downloadTemperatureData(String city)
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl.replace("{apiKey}",apiKey).replace("{cityName}",city);
        String response = restTemplate.getForObject(url,String.class);
        return response;
    }
}
