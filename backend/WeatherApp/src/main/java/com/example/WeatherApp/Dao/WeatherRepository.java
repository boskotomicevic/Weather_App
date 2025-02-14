package com.example.WeatherApp.Dao;

import com.example.WeatherApp.Entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Integer> {
    Weather findTopByOrderByIdDesc();
}
