
CREATE TABLE IF NOT EXISTS cities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    latitude DECIMAL(10, 8) NOT NULL,
    longitude DECIMAL(11, 8) NOT NULL,
    UNIQUE KEY unique_city_name (name)
);
CREATE TABLE IF NOT EXISTS forecasts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city_id BIGINT NOT NULL,
    forecast_type VARCHAR(50) NOT NULL,
    timestamp VARCHAR(255) NOT NULL,
    data JSON NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE
);
CREATE INDEX idx_city_forecast_type ON forecasts(city_id, forecast_type);
