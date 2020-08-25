package com.egen.consumerWeatherApp.service;

import java.util.List;

import com.egen.consumerWeatherApp.model.WeatherAlert;

public interface AlertService {
boolean addAlerts(WeatherAlert weatherAlert);
List<WeatherAlert> getAllAlerts();
}
