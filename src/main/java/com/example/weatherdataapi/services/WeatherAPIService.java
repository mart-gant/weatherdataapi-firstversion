package com.example.weatherdataapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherAPIService {
    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    public String getWeatherData(double lat, double lon, String forecastType) {
        String url = String.format("%s?lat=%f&lon=%f&type=%s&appid=%s", apiUrl, lat, lon, forecastType, apiKey);
        return restTemplate.getForObject(url, String.class);
    }
}

