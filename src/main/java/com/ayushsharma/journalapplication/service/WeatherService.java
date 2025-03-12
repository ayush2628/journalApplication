package com.ayushsharma.journalapplication.service;


import com.ayushsharma.journalapplication.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String api_key = "af9a52ac63dd522916a7ce9b93981df8";
    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finalApi = API.replace("CITY", city).replace("API_KEY", api_key);
        ResponseEntity<WeatherResponse> ApiResponse = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = ApiResponse.getBody();
        return body;
    }


}
