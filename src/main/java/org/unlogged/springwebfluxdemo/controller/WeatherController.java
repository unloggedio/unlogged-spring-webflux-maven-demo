package org.unlogged.springwebfluxdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.unlogged.springwebfluxdemo.model.weather.WeatherInfo;
import org.unlogged.springwebfluxdemo.service.WeatherService;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{address}")
    public Mono<WeatherInfo> getWeather(@PathVariable String address) {
        return weatherService.getWeatherForAddress(address)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather info not found")));
    }
}