package com.example.weatherdataapi.services;

import com.example.weatherdataapi.dto.ForecastDTO;
import com.example.weatherdataapi.exception.CityNotFoundException;
import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.models.Forecast;
import com.example.weatherdataapi.repositories.ForecastRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastService {

    private final ForecastRepository forecastRepository;
    private final CityService cityService;

    public ForecastService(ForecastRepository forecastRepository, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.cityService = cityService;
    }

    public List<Forecast> getForecastForCity(Long cityId, String type) {
        City city = cityService.getCityById(cityId);
        return forecastRepository.findByCityAndType(city, type);
    }

    public Forecast updateForecast(Long cityId, ForecastDTO forecastDTO) {
        City city = cityService.getCityById(cityId);
        if (city == null) {
            throw new CityNotFoundException("City not found");
        }

        Forecast forecast = new Forecast();
        forecast.setCity(city);
        forecast.setForecastType(forecastDTO.getForecastType());
        forecast.setTimestamp(forecastDTO.getTimestamp());
        forecast.setData(forecastDTO.getData());

        return forecastRepository.save(forecast);
    }

    public Forecast saveForecast(Forecast forecast) {
        return forecastRepository.save(forecast);
    }
}
