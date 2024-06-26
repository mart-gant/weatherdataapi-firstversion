Requirements:
Java 17 or later
Maven
OpenWeatherMap account with API key
Installation and Start-up
Clone repository:
git clone https://github.com/twoje-konto/weather-api.git
cd weather-api
API Key Configuration:
Configure your OpenWeatherMap API key by adding it to application.properties or setting it as an environment variable:
# src/main/resources/application.properties
weather.api.key=TWÓJ_API_KLUCZ
Build the project and run the application:
mvn clean install
mvn spring-boot:run
The application will be available at http://localhost:8080.
API
Operations
List cities:
GET /api/cities
Description: Gets a list of all saved cities.
Download the hourly forecast:
GET /api/cities/{cityId}/forecast/hourly
Description: Gets the hourly forecast for the selected city.
Download the 2-day forecast:
GET /api/cities/{cityId}/forecast/2-day
Description: Gets the 2-day forecast for the selected city.
Download the 8-day forecast:
GET /api/cities/{cityId}/forecast/8-day
Description: Gets an 8-day forecast for the selected city.
Update forecast:
PUT /api/cities/{cityId}/forecast
Description: Updates the forecast for the selected city.
Validation
Each request should contain valid data and meet validation requirements, otherwise an appropriate error message will be returned.
Testing
The project contains unit tests written using JUnit 5 and Mockito.
Running Tests
To run unit tests:
mvn test
Usage Examples
Below are examples of how to call the API using curl or Postman:
List of cities:
curl -X GET http://localhost:8080/api/cities
Downloading the hourly forecast:
curl -X GET http://localhost:8080/api/cities/1/forecast/hourly
Forecast update:
curl -X PUT http://localhost:8080/api/cities/1/forecast -H "Content-Type: application/json" -d '{"forecastType": "hourly", "timestamp": "2024-06-25T12:00:00", "data": "{\"temp\":20}"}'

