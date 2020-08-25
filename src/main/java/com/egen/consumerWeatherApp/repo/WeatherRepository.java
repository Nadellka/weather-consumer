package com.egen.consumerWeatherApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egen.consumerWeatherApp.model.Weather;
@Repository
public interface WeatherRepository extends JpaRepository<Weather, String> {

}
