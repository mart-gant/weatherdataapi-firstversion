package com.example.weatherdataapi.controllers;

import com.example.weatherdataapi.dto.ForecastDTO;
import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.models.Forecast;
import com.example.weatherdataapi.services.CityService;
import com.example.weatherdataapi.services.ForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final CityService cityService;
    private final ForecastService forecastService;

    public WeatherController(CityService cityService, ForecastService forecastService) {
        this.cityService = cityService;
        this.forecastService = forecastService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> listCities() {
        List<City> cities = cityService.listAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/forecast/{cityId}/{type}")
    public ResponseEntity<List<Forecast>> getForecast(@PathVariable Long cityId, @PathVariable String type) {
        List<Forecast> forecasts = forecastService.getForecastForCity(cityId, type);
        return ResponseEntity.ok(forecasts);
    }

    @PostMapping("/echo")
    public ResponseEntity<String> echoEntity(@RequestBody String entity) {
        return ResponseEntity.ok(entity);
    }

    @PostMapping("/update/{cityId}")
    public ResponseEntity<Forecast> updateForecast(@PathVariable Long cityId, @RequestBody ForecastDTO forecastDTO) {
        Forecast updatedForecast = forecastService.updateForecast(cityId, forecastDTO);
        return ResponseEntity.ok(updatedForecast);
    }
}
