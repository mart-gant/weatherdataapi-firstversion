package com.example.weatherdataapi.config;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.tools.Server;

import java.sql.SQLException;

@Configuration
public class AppConfig {
    @Bean
    public com.example.weatherdataapi.config.WireMockServer wireMockServer() {
        com.example.weatherdataapi.config.WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
        return wireMockServer;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
