
package com.weather.analysis.service;

import com.weather.analysis.entity.WeatherData;
import com.weather.analysis.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository repository;

    public List<WeatherData> getByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return repository.findByDateTimeBetween(start, end);
    }

    public Map<String, Double> getTemperatureStats(int year, int month) {
        List<Double> temps = repository.findTemperaturesByYearAndMonth(year, month);
        temps.removeIf(Objects::isNull);
        if (temps.isEmpty()) return Map.of();

        Collections.sort(temps);
        double min = temps.get(0);
        double max = temps.get(temps.size() - 1);
        double median = temps.get(temps.size() / 2);

        Map<String, Double> map = new HashMap<>();
        map.put("min", min);
        map.put("max", max);
        map.put("median", median);
        return map;
    }
}
