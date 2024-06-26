package com.example.weatherdataapi.services;

import com.example.weatherdataapi.exception.CityNotFoundException;
import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cityService = new CityService(cityRepository);
    }

    @Test
    public void testGetCityById_Success() {
        City city = new City();
        city.setId(1L);
        city.setName("Test City");

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        City foundCity = cityService.getCityById(1L);

        assertNotNull(foundCity);
        assertEquals("Test City", foundCity.getName());
    }

    @Test
    public void testGetCityById_NotFound() {
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());

        CityNotFoundException exception = assertThrows(CityNotFoundException.class, () -> cityService.getCityById(1L));
        assertEquals("City with ID 1 not found", exception.getMessage());
    }
}
