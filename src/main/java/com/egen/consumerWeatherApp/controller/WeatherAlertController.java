package com.egen.consumerWeatherApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egen.consumerWeatherApp.model.WeatherAlert;
import com.egen.consumerWeatherApp.service.AlertService;

@RestController
public class WeatherAlertController {
	
	private AlertService alertService;
	
	@Autowired
	public WeatherAlertController(AlertService alertService) {
		this.alertService=alertService;
	}
	@PostMapping("/addReadings")
	public boolean addWeatherReadings(@RequestBody WeatherAlert weatherAlert) {
		
		//System.out.println(weatherAlert);
		alertService.addAlerts(weatherAlert);
		return true;
		
		
	}
	
	

}
