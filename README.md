# weather-analysis-project

## Run Steps
1. Create MySQL database: weather_db
2. Update username/password in application.properties
3. Place CSV file as testset.csv in project root
4. mvn spring-boot:run

## APIs
GET /api/weather/by-date?date=YYYY-MM-DD
GET /api/weather/temperature-stats?year=YYYY&month=MM
