package com.example.weatherdataapi.services;

import com.example.weatherdataapi.exception.CityNotFoundException;
import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> listAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found"));
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }
}
