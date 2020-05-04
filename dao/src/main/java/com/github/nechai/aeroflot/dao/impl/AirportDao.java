package com.github.nechai.aeroflot.dao.impl;
import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.IAirportDao;
import com.github.nechai.aeroflot.dao.converter.AirportConverter;
import com.github.nechai.aeroflot.dao.entity.AirportEntity;
import com.github.nechai.aeroflot.model.Airport;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
    public List<Airport> getListAirport() {
        List <Airport> airports=new ArrayList<>();
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from AirportEntity where actFl=:paramActFl");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
    // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
    // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List <AirportEntity> airportEntityList=(List <AirportEntity>)query.list();
        for (AirportEntity a:airportEntityList) {
            airports.add(AirportConverter.fromEntity(a));
        }
        return airports;
    }

    public Airport getAirportById(int airportId) {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from AirportEntity where id=:paramId");
        query.setParameter("paramId", airportId);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        return AirportConverter.fromEntity((AirportEntity) query.uniqueResult());
    }
 }
