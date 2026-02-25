
package com.weather.analysis.controller;

import com.weather.analysis.entity.WeatherData;
import com.weather.analysis.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService service;

    @GetMapping("/by-date")
    public List<WeatherData> byDate(@RequestParam String date) {
        return service.getByDate(LocalDate.parse(date));
    }

    @GetMapping("/temperature-stats")
    public Map<String, Double> stats(@RequestParam int year, @RequestParam int month) {
        return service.getTemperatureStats(year, month);
    }
}
