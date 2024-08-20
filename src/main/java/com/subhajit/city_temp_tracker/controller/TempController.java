package com.subhajit.city_temp_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/internal/city")
public class TempController{

    @GetMapping
    public ResponseEntity<Object> getTempResponse() {
        System.out.println("Request received");
        Map<String,String> responses = new HashMap<>();
        responses.put("Kolkata","21 C");
        responses.put("Berlin","2 C");
        return ResponseEntity.ok((Object) responses);
    }
}
