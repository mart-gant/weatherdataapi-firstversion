package com.example.weatherdataapi.config;

public record WireMockServer(com.github.tomakehurst.wiremock.core.WireMockConfiguration wireMockConfiguration) {
    public void start() {

    }
}
