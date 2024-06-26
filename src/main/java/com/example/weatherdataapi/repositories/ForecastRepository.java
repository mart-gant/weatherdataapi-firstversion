package com.example.weatherdataapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.models.Forecast;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    List<Forecast> findByCityAndForecastType(City city, String forecastType);

    List<Forecast> findByCityAndType(City city, String type);
}

