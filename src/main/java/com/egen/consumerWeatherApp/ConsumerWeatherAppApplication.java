package com.egen.consumerWeatherApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.egen.consumerWeatherApp.aws.WeatherAlertsListenerSqs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
public class ConsumerWeatherAppApplication {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		//SpringApplication.run(ConsumerWeatherAppApplication.class, args);
		ApplicationContext applicationContext=SpringApplication.run(ConsumerWeatherAppApplication.class, args);
		WeatherAlertsListenerSqs weatherAlertsListenerSqs= applicationContext.getBean(WeatherAlertsListenerSqs.class);
		weatherAlertsListenerSqs.startListeningToMessages();
	}

}
