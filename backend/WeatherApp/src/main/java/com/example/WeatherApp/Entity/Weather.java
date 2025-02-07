package com.example.WeatherApp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weather_app")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "weather_location")
    private String weatherLocation;
    @Column(name = "weather_temperature")
    private int weatherTemperature;
    @Column(name = "weather_outfit_recomendation")
    private String weatherOutfitRecomendation;

    public Weather() {
    }

    public Weather(String weatherLocation, int weatherTemperature, String weatherOutfitRecomendation) {
        this.weatherLocation = weatherLocation;
        this.weatherTemperature = weatherTemperature;
        this.weatherOutfitRecomendation = weatherOutfitRecomendation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeatherLocation() {
        return weatherLocation;
    }

    public void setWeatherLocation(String weatherLocation) {
        this.weatherLocation = weatherLocation;
    }

    public int getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(int weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public String getWeatherOutfitRecomendation() {
        return weatherOutfitRecomendation;
    }

    public void setWeatherOutfitRecomendation(String weatherOutfitRecomendation) {
        this.weatherOutfitRecomendation = weatherOutfitRecomendation;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", weatherLocation='" + weatherLocation + '\'' +
                ", weatherTemperature=" + weatherTemperature +
                ", weatherOutfitRecomendation='" + weatherOutfitRecomendation + '\'' +
                '}';
    }
}
