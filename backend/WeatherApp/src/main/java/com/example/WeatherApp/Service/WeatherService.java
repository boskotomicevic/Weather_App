package com.example.WeatherApp.Service;

import com.example.WeatherApp.Entity.Weather;

public interface WeatherService {
     Weather save(Weather weather);
     Weather getLatestWeather(Weather weatherData);
}
