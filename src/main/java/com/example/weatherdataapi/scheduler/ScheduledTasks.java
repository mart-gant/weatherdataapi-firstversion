package com.example.weatherdataapi.scheduler;

import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.models.Forecast;
import com.example.weatherdataapi.services.CityService;
import com.example.weatherdataapi.services.ForecastService;
import com.example.weatherdataapi.services.WeatherAPIService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    private final CityService cityService;
    private final WeatherAPIService weatherAPIService;
    private final ForecastService forecastService;

    public ScheduledTasks(CityService cityService, WeatherAPIService weatherAPIService, ForecastService forecastService) {
        this.cityService = cityService;
        this.weatherAPIService = weatherAPIService;
        this.forecastService = forecastService;
    }

    @Scheduled(fixedRate = 300000) // every 5 minutes
    public void updateHourlyForecasts() {
        updateForecasts("hourly");
    }

    @Scheduled(fixedRate = 3600000) // every hour
    public void updateTwoDayForecasts() {
        updateForecasts("2day");
    }

    @Scheduled(fixedRate = 86400000) // every day
    public void updateEightDayForecasts() {
        updateForecasts("8day");
    }

    private void updateForecasts(String type) {
        List<City> cities = cityService.listAllCities();
        for (City city : cities) {
            String data = weatherAPIService.getWeatherData(city.getLatitude(), city.getLongitude(), type);
            Forecast forecast = new Forecast();
            forecast.setCity(city);
            forecast.setForecastType(type);
            forecast.setTimestamp(LocalDateTime.now());
            forecast.setData(data);
            forecastService.saveForecast(forecast);
        }
    }
}
