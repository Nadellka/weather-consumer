package com.egen.consumerWeatherApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.consumerWeatherApp.model.WeatherAlert;
import com.egen.consumerWeatherApp.repo.AlertRepository;
@Service
public class DefaultAlertService implements AlertService {
	
	private AlertRepository alertRepository;
	@Autowired
	public DefaultAlertService(AlertRepository alertRepository) {
		this.alertRepository=alertRepository;
	}

	@Override
	public boolean addAlerts(WeatherAlert weatherAlert) {
		alertRepository.save(weatherAlert);
		return true;
	}

	@Override
	public List<WeatherAlert> getAllAlerts() {
		List<WeatherAlert> weatherAlertList= alertRepository.findAll();
		return weatherAlertList;
	}

}
