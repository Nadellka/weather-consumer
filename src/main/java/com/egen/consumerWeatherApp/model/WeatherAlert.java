package com.egen.consumerWeatherApp.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WeatherAlert {
	@Id
	private String id;
	private String alert;
	@OneToOne(cascade=CascadeType.ALL)
	private Weather weather;
	public WeatherAlert() {
		
		this.id = UUID.randomUUID().toString();
		
	}
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}


	@Override
	public String toString() {
		return "WeatherAlert [id=" + id + ", alert=" + alert + ", weather=" + weather + "]";
	}
	
	
	
	
}
