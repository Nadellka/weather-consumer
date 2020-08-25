package com.egen.consumerWeatherApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egen.consumerWeatherApp.model.Wind;
@Repository
public interface WindRepository extends JpaRepository<Wind, String> {

}
