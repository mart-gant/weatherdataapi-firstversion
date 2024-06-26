package com.example.weatherdataapi.exception;

public class ForecastNotFoundException extends RuntimeException {

    public ForecastNotFoundException(String message) {
        super(message);
    }

    public ForecastNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
