package com.example.weatherdataapi.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockConfiguration {

    @Bean
    public WireMockServer wireMockServer(Object Options) {
        WireMockServer server = new WireMockServer(Options.dynamicPort());
        server.start();
        return server;
    }
}
