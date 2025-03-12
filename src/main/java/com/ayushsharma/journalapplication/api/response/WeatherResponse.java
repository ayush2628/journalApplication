package com.ayushsharma.journalapplication.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
public class WeatherResponse{


    public Current current;

    public class Current{
        public int temperature;

        @JsonProperty("weather_descriptions")
        public List<String> weatherDescriptions;

        public int feelslike;

    }


}









