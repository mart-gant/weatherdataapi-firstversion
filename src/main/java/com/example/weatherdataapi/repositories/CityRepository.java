package com.example.weatherdataapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weatherdataapi.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
