package com.subhajit.city_temp_tracker.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

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

    @Field(type = FieldType.Date)
    private Instant time;
}