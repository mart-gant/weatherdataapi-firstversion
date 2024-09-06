package com.example.weatherdataapi.dto;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public class ForecastDTO {
    private String forecastType;

    @NotNull
    private LocalDateTime timestamp;

    private String data;

    public ForecastDTO() {
        this.forecastType = null;
    }

    public String getForecastType() {
        return forecastType;
    }

    public void setForecastType(Object forecastType) {
        this.forecastType = (String) forecastType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

