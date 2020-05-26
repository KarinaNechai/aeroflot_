package com.github.nechai.aeroflot.dao.impl;
import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.IAirportDao;
import com.github.nechai.aeroflot.dao.converter.AirportConverter;
import com.github.nechai.aeroflot.dao.entity.AirportEntity;
import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Page;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AirportDao implements IAirportDao {
    private static volatile AirportDao instance;

    private AirportDao() {
    }

    public static AirportDao getInstance() {
        AirportDao localInstance = instance;
        if (localInstance == null) {
            synchronized (AirportDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AirportDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int save(Airport airport) {
        AirportEntity airportEntity = AirportConverter.toEntity(airport);
        final Session session = HibernateUtil.getSession();
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
        List <Airport> airports=new ArrayList<>();
        final Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AirportEntity> criteria = cb.createQuery(AirportEntity.class);
        Root<AirportEntity> air=criteria.from(AirportEntity.class);
        criteria.select(air).where(cb.equal(air.get("actFl"),1)).orderBy(cb.asc(air.get("name")));
        TypedQuery<AirportEntity> typedQuery=session.createQuery(criteria);
        typedQuery.setFirstResult(page.getFirst());
        typedQuery.setMaxResults(page.getMax());
        List<AirportEntity> airportEntityList=typedQuery.getResultList();
        for (AirportEntity a:airportEntityList) {
            airports.add(AirportConverter.fromEntity(a));
        }
        return airports;
    }

    @Override
    public List<Airport> getListAirport() {
        List <Airport> airports=new ArrayList<>();
        final Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AirportEntity> criteria = cb.createQuery(AirportEntity.class);
        Root<AirportEntity> air=criteria.from(AirportEntity.class);
        criteria.select(air).where(cb.equal(air.get("actFl"),1)).orderBy(cb.asc(air.get("name")));
        List<AirportEntity> airportEntityList=session.createQuery(criteria).getResultList();
        for (AirportEntity a:airportEntityList) {
            airports.add(AirportConverter.fromEntity(a));
        }
        return airports;
    }

    @Override
    public int getCountOfAirports() {
        final Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        criteria.select(cb.count(criteria.from(AirportEntity.class)));
        long count = session.createQuery(criteria).getSingleResult();
        return (int) count;
    }

    public Airport getAirportById(int airportId) {
        final Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AirportEntity> criteria = cb.createQuery(AirportEntity.class);
        Root<AirportEntity> air=criteria.from(AirportEntity.class);
        criteria.select(air).where(cb.equal(air.get("id"),airportId));
         AirportEntity a=session.createQuery(criteria).getSingleResult();
        return AirportConverter.fromEntity(a);

    }
 }
