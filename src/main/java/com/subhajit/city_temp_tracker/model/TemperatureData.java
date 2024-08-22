package com.subhajit.city_temp_tracker.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(indexName="temperature_data")
public class TemperatureData{
    @Id
    private String id;
    private String city;
    private String extraInfo;
    private String time;

}