package com.example.weatherdataapi.controllers;

import com.example.weatherdataapi.dto.ForecastDTO;
import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.models.Forecast;
import com.example.weatherdataapi.services.CityService;
import com.example.weatherdataapi.services.ForecastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WeatherControllerTest {

    @Mock
    private CityService cityService;

    @Mock
    private ForecastService forecastService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherController = new WeatherController(cityService, forecastService);
    }

    @Test
    public void testListCities() {
        City city = new City();
        city.setId(1L);
        city.setName("Test City");

        when(cityService.listAllCities()).thenReturn(Collections.singletonList(city));

        ResponseEntity<List<City>> response = weatherController.listCities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Test City", response.getBody().get(0).getName());
    }

    @Test
    public void testGetForecast() {
        Forecast forecast = new Forecast();
        forecast.setForecastType("hourly");

        when(forecastService.getForecastForCity(1L, "hourly")).thenReturn(Collections.singletonList(forecast));

        ResponseEntity<List<Forecast>> response = weatherController.getForecast(1L, "hourly");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("hourly", response.getBody().get(0).getForecastType());
    }

    @Test
    public void testUpdateForecast() {
        ForecastDTO forecastDTO = new ForecastDTO();
        forecastDTO.setForecastType("hourly");
        forecastDTO.setTimestamp(LocalDateTime.parse("2024-06-25T12:00:00"));
        forecastDTO.setData("{\"temp\":20}");

        Forecast forecast = new Forecast();
        forecast.setForecastType("hourly");
        forecast.setTimestamp(LocalDateTime.parse("2024-06-25T12:00:00"));
        forecast.setData("{\"temp\":20}");

        when(forecastService.updateForecast(1L, forecastDTO)).thenReturn(forecast);

        ResponseEntity<Forecast> response = weatherController.updateForecast(1L, forecastDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("hourly", Objects.requireNonNull(response.getBody()).getForecastType());
        assertEquals("2024-06-25T12:00:00", response.getBody().getTimestamp());
    }
}
