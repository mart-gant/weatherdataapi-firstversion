package com.example.weatherdataapi;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherDataApiApplicationTests {

    @LocalServerPort
    private final int port;

    @Autowired
    private final TestRestTemplate restTemplate;

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance().options(WireMockConfiguration.wireMockConfig().dynamicPort()).build();

    WeatherDataApiApplicationTests(int port, TestRestTemplate restTemplate) {
        this.port = port;
        this.restTemplate = restTemplate;
    }

    @Test
    void contextLoads() {
        assertThat(port).isGreaterThan(0);
    }

    @Test
    void wireMockServerIsRunning() {
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/test")).willReturn(WireMock.aResponse().withStatus(200)));
        String response = this.restTemplate.getForObject("http://localhost:" + wireMockServer.getPort() + "/test", String.class);
        assertThat(response).isNotNull();
    }
}
