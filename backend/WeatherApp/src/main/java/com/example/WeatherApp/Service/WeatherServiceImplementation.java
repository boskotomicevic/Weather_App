package com.example.WeatherApp.Service;

import com.example.WeatherApp.Dao.WeatherRepository;
import com.example.WeatherApp.Entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImplementation implements WeatherService{
    private WeatherRepository wr;

    @Autowired
    public WeatherServiceImplementation(WeatherRepository wr){
        this.wr = wr;
    }
    @Override
    public Weather save(Weather newWeather){
        return wr.save(newWeather);
    }
    @Override
    public Weather getLatestWeather(Weather weatherData){
        return wr.findTopByOrderByIdDesc();
    }
}
