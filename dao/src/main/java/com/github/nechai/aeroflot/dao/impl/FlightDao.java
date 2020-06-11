package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.IFlightDao;
import com.github.nechai.aeroflot.dao.converter.FlightConverter;
import com.github.nechai.aeroflot.dao.entity.FlightEntity;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightDao implements IFlightDao {
    private final SessionFactory factory;

    public FlightDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public int save(Flight flight) {
        FlightEntity flightEntity = FlightConverter.toEntity(flight);
        final Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(flightEntity);
        session.getTransaction().commit();
        return flightEntity.getId();
    }

    @Override
    public Flight getFlightById(int flightId) {
        Flight flight = null;
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("from FlightEntity where id=:paramId");
        query.setParameter("paramId", flightId);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        return FlightConverter.fromEntity((FlightEntity) query.uniqueResult());
    }

    public List<Flight> getListFlight() {
        List<Flight> flights = new ArrayList<Flight>();
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("from FlightEntity where actFl=:paramActFl");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List<FlightEntity> flightEntityList = (List<FlightEntity>) query.list();
        for (FlightEntity f : flightEntityList) {
            flights.add(FlightConverter.fromEntity(f));
        }
        return flights;
    }

    public List<Flight> getListFlight(Page page) {
        List<Flight> flights = new ArrayList<Flight>();
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("from FlightEntity where actFl=:paramActFl order by datetime asc ");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        query.setFirstResult(page.getFirst());
        query.setMaxResults(page.getMax());
        List<FlightEntity> flightEntityList = (List<FlightEntity>) query.list();
        for (FlightEntity f : flightEntityList) {
            flights.add(FlightConverter.fromEntity(f));
        }
        return flights;
    }

    @Override
    public int delete(int flightId) {
        Flight flight = getFlightById(flightId);
        flight.setActual(false);
        return save(flight);
    }

    public int getCountOfFlights() {
        final Session session = factory.getCurrentSession();
        Query query = session.createQuery("select count(*) from FlightEntity where actFl=:paramActFl");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT).
                // сущности и коллекции помечаюся как только для чтения
                        setReadOnly(true);
        long count = (Long) query.getSingleResult();
        return (int) count;
    }
}
