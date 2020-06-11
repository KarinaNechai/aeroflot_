package com.github.nechai.aeroflot.service.config;

import com.github.nechai.aeroflot.dao.config.DaoConfig;
import com.github.nechai.aeroflot.model.Plane;
import com.github.nechai.aeroflot.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    public UserService userService(){
        return new UserService(daoConfig.userDao());
    }

    @Bean
    public AirportService airportService(){
        return new AirportService(daoConfig.airportDao());
    }
    @Bean
    public PlaneService planeService(){
        return new PlaneService(daoConfig.planeDao());
    }
    @Bean
    public FlightService flightService(){
        return new FlightService(daoConfig.flightDao());
    }
    @Bean
    public WorkerService workerService(){
        return new WorkerService(daoConfig.workerDao());
    }
}