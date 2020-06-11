package com.github.nechai.aeroflot.dao.config;

import com.github.nechai.aeroflot.dao.*;
import com.github.nechai.aeroflot.dao.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
public class DaoConfig {

    private final SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public IAirportDao airportDao() {
        return new AirportDao(sessionFactory);
    }

    @Bean
    public IFlightDao flightDao() {
        return new FlightDao(sessionFactory);
    }

    @Bean
    public IPlaneDao planeDao() {
        return new PlaneDao(sessionFactory);
    }

    @Bean
    public IUserDao userDao() {
        return new UserDao(sessionFactory);
    }

    @Bean
    public IWorkerDao workerDao() {
        return new WorkerDao(sessionFactory);
    }

}
