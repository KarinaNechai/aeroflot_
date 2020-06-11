package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.IAirportDao;
import com.github.nechai.aeroflot.dao.converter.AirportConverter;
import com.github.nechai.aeroflot.dao.entity.AirportEntity;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AirportDao implements IAirportDao {
    private final SessionFactory factory;

    public AirportDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public int save(Airport airport) {
        AirportEntity airportEntity = AirportConverter.toEntity(airport);
        final Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(airportEntity);
        session.getTransaction().commit();
        return airportEntity.getId();
    }

    @Override
    public int delete(Airport airport) {
        airport.setActual(false);
        return save(airport);
    }

    @Override
    public int delete(int airportId) {
        return save(getAirportById(airportId));
    }

    @Override
    public List<Airport> getListAirport(Page page) {
        List<Airport> airports = new ArrayList<>();
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AirportEntity> criteria = cb.createQuery(AirportEntity.class);
        Root<AirportEntity> air = criteria.from(AirportEntity.class);
        criteria.select(air).where(cb.equal(air.get("actFl"), 1)).orderBy(cb.asc(air.get("name")));
        TypedQuery<AirportEntity> typedQuery = session.createQuery(criteria);
        typedQuery.setFirstResult(page.getFirst());
        typedQuery.setMaxResults(page.getMax());
        List<AirportEntity> airportEntityList = typedQuery.getResultList();
        for (AirportEntity a : airportEntityList) {
            airports.add(AirportConverter.fromEntity(a));
        }
        return airports;
    }

    @Override
    public List<Airport> getListAirport() {
        List<Airport> airports = new ArrayList<>();
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AirportEntity> criteria = cb.createQuery(AirportEntity.class);
        Root<AirportEntity> air = criteria.from(AirportEntity.class);
        criteria.select(air).where(cb.equal(air.get("actFl"), 1)).orderBy(cb.asc(air.get("name")));
        List<AirportEntity> airportEntityList = session.createQuery(criteria).getResultList();
        for (AirportEntity a : airportEntityList) {
            airports.add(AirportConverter.fromEntity(a));
        }
        return airports;
    }

    @Override
    public int getCountOfAirports() {
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.count(criteria.from(AirportEntity.class)));
        long count = session.createQuery(criteria).getSingleResult();
        return (int) count;
    }

    public Airport getAirportById(int airportId) {
        final Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AirportEntity> criteria = cb.createQuery(AirportEntity.class);
        Root<AirportEntity> air = criteria.from(AirportEntity.class);
        criteria.select(air).where(cb.equal(air.get("id"), airportId));
        AirportEntity a = session.createQuery(criteria).getSingleResult();
        return AirportConverter.fromEntity(a);

    }
}
