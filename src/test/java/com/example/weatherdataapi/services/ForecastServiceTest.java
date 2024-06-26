package com.example.weatherdataapi.services;

import com.example.weatherdataapi.dto.ForecastDTO;
import com.example.weatherdataapi.exception.CityNotFoundException;
import com.example.weatherdataapi.exception.ForecastNotFoundException;
import com.example.weatherdataapi.models.City;
import com.example.weatherdataapi.models.Forecast;
import com.example.weatherdataapi.repositories.ForecastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ForecastServiceTest {

    @Mock
    private ForecastRepository forecastRepository;

    @Mock
    private CityService cityService;

    @InjectMocks
    private ForecastService forecastService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        forecastService = new ForecastService(forecastRepository, cityService);
    }

    @Test
    public void testGetForecastForCity_Success() {
        City city = new City();
        city.setId(1L);

        Forecast forecast = new Forecast();
        forecast.setCity(city);
        forecast.setForecastType("hourly");

        when(cityService.getCityById(1L)).thenReturn(city);
        when(forecastRepository.findByCityAndType(city, "hourly")).thenReturn(Collections.singletonList(forecast));

        List<Forecast> forecasts = forecastService.getForecastForCity(1L, "hourly");

        assertNotNull(forecasts);
        assertEquals(1, forecasts.size());
        assertEquals("hourly", forecasts.get(0).getForecastType());
    }

    @Test
    public void testGetForecastForCity_NotFound() {
        City city = new City();
        city.setId(1L);

        when(cityService.getCityById(1L)).thenReturn(city);
        when(forecastRepository.findByCityAndType(city, "hourly")).thenReturn(Collections.emptyList());

        ForecastNotFoundException exception = assertThrows(ForecastNotFoundException.class, () -> forecastService.getForecastForCity(1L, "hourly"));
        assertEquals("Forecast for city ID 1 with type hourly not found", exception.getMessage());
    }

    @Test
    public void testUpdateForecast_Success() {
        City city = new City();
        city.setId(1L);
        city.setName("Test City");

        ForecastDTO forecastDTO = new ForecastDTO();
        forecastDTO.setForecastType("hourly");
        forecastDTO.setTimestamp(LocalDateTime.parse("2024-06-25T12:00:00"));
        forecastDTO.setData("{\"temp\":20}");

        when(cityService.getCityById(1L)).thenReturn(city);

        Forecast forecast = new Forecast();
        forecast.setCity(city);
        forecast.setForecastType("hourly");
        forecast.setTimestamp(LocalDateTime.parse("2024-06-25T12:00:00"));
        forecast.setData("{\"temp\":20}");

        when(forecastRepository.save(any(Forecast.class))).thenReturn(forecast);

        Forecast updatedForecast = forecastService.updateForecast(1L, forecastDTO);

        assertNotNull(updatedForecast);
        assertEquals("hourly", updatedForecast.getForecastType());
        assertEquals("2024-06-25T12:00:00", updatedForecast.getTimestamp());
    }

    @Test
    public void testUpdateForecast_CityNotFound() {
        ForecastDTO forecastDTO = new ForecastDTO();
        forecastDTO.setForecastType("hourly");
        forecastDTO.setTimestamp(LocalDateTime.parse("2024-06-25T12:00:00"));
        forecastDTO.setData("{\"temp\":20}");

        when(cityService.getCityById(1L)).thenThrow(new CityNotFoundException("City not found"));

        CityNotFoundException exception = assertThrows(CityNotFoundException.class, () -> forecastService.updateForecast(1L, forecastDTO));
        assertEquals("City not found", exception.getMessage());
    }
}
