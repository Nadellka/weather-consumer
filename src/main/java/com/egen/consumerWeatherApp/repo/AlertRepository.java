package com.egen.consumerWeatherApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egen.consumerWeatherApp.model.WeatherAlert;

public interface AlertRepository extends JpaRepository<WeatherAlert, String> {

}
