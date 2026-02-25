
package com.weather.analysis.util;

import com.opencsv.CSVReader;
import com.weather.analysis.entity.WeatherData;
import com.weather.analysis.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class CsvLoader {

    private final WeatherRepository repository;

    @Bean
    CommandLineRunner loadCsv() {
        return args -> {
            try (CSVReader reader = new CSVReader(new FileReader("testset.csv"))) {
                String[] header = reader.readNext();
                String[] line;
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                while ((line = reader.readNext()) != null) {
                    try {
                        WeatherData data = WeatherData.builder()
                                .dateTime(LocalDateTime.parse(line[0], fmt))
                                .condition(line[1])
                                .temperature(parse(line[2]))
                                .humidity(parse(line[3]))
                                .pressure(parse(line[4]))
                                .heatIndex(parse(line[5]))
                                .build();
                        repository.save(data);
                    } catch (Exception ignored) {}
                }
            } catch (Exception e) {
                System.out.println("CSV load skipped: " + e.getMessage());
            }
        };
    }

    private Double parse(String s) {
        try { return Double.parseDouble(s); }
        catch (Exception e) { return null; }
    }
}
