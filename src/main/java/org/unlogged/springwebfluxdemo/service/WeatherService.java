package org.unlogged.springwebfluxdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.model.weather.WeatherInfo;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public WeatherService(ObjectMapper objectMapper) {
        this.webClient = WebClient.create("http://api.weatherapi.com/v1");
        this.objectMapper = objectMapper;
    }

    public Mono<WeatherInfo> getWeatherForAddress(String address) {
        return getWeatherInfo(address)
                .map(this::convertToObject)
                .onErrorResume(ex -> {
                    // Handle exceptions here
                    ex.printStackTrace();
                    return Mono.empty();
                });
    }

    private Mono<String> getWeatherInfo(String location) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/current.json")
                        .queryParam("key", "09282a8b683349e79f852552230102")
                        .queryParam("q", location)
                        .queryParam("aqi", "no")
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response -> {
                    // Handle 4xx errors (client errors)
                    return Mono.error(new RuntimeException("Client error: " + response.statusCode()));
                })
                .onStatus(status -> status.is5xxServerError(), response -> {
                    // Handle 5xx errors (server errors)
                    return Mono.error(new RuntimeException("Server error: " + response.statusCode()));
                })
                .bodyToMono(String.class)
                .onErrorResume(ex -> {
                    // Handle exceptions from WebClient operations
                    ex.printStackTrace();
                    return Mono.empty();
                });
    }

    public WeatherInfo convertToObject(String info) {
        try {
            return objectMapper.readValue(info, WeatherInfo.class);
        } catch (Exception e) {
            // Handle JSON parsing errors
            e.printStackTrace();
            return null;
        }
    }

    public String getLocationFromAddress(String address) {
        String[] parts = address.split(" ");
        if (parts.length >= 1) {
            return parts[parts.length - 1];
        }
        return address;
    }
}
