
package com.weather.analysis.repository;

import com.weather.analysis.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT w.temperature FROM WeatherData w WHERE YEAR(w.dateTime) = :year AND MONTH(w.dateTime) = :month")
    List<Double> findTemperaturesByYearAndMonth(int year, int month);
}
