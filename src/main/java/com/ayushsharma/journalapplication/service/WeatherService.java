package com.ayushsharma.journalapplication.service;


import com.ayushsharma.journalapplication.api.response.WeatherResponse;
import com.ayushsharma.journalapplication.cache.AppCache;
import com.ayushsharma.journalapplication.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private  String api_key ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        String finalApi = AppCache.appCache.get("weather_api").replace(Placeholders.City, city).replace(Placeholders.Api_key, api_key);
        ResponseEntity<WeatherResponse> ApiResponse = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = ApiResponse.getBody();
        return body;
    }


}
