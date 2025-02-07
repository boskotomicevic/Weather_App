package com.example.WeatherApp.Rest;

import com.example.WeatherApp.Entity.Weather;
import com.example.WeatherApp.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService ws;
    @Autowired
    public WeatherController(WeatherService ws){
        this.ws = ws;
    }

    @PostMapping("/save")
    public Weather save(Weather weatherData){
        return ws.save(weatherData);
    }
    @GetMapping("/latest")
    public Weather getLatestWeather(Weather weatherData){
        return ws.getLatestWeather(weatherData);
    }



}
