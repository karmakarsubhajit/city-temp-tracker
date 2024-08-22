package com.subhajit.city_temp_tracker.repository;

import com.subhajit.city_temp_tracker.model.TemperatureData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends ElasticsearchRepository<TemperatureData, String> {
    List<TemperatureData> findByCity(String city);
}
