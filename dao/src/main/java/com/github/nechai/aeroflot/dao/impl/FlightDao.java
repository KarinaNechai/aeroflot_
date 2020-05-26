package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.IFlightDao;
import com.github.nechai.aeroflot.dao.converter.FlightConverter;
import com.github.nechai.aeroflot.dao.entity.FlightEntity;
import com.github.nechai.aeroflot.model.Flight;
import com.github.nechai.aeroflot.model.Page;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class FlightDao implements IFlightDao {
    private static volatile FlightDao instance;
    private FlightDao() {
    }

    public static FlightDao getInstance() {
        FlightDao localInstance = instance;
        if (localInstance == null) {
            synchronized (FlightDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FlightDao();
                }
            }
        }
        return localInstance;
    }

  /*   public boolean insert(int airportFrom, int airportTo, LocalDateTime date) {
            String str= "INSERT INTO myapp.flight (airportfrom,airportto,flightdate) VALUES (?,?,?)";
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String s=format(date);
        java.util.Date utilDate = date;
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
             try {
                Connection connection = DataSource.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(str);
                ps.setInt(1, airportFrom);
                ps.setInt(2,airportTo);
                ps.setDate(3, sqlDate);
                return (ps.executeUpdate() == 1);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

    }*/
    @Override
    public int save(Flight flight) {
        FlightEntity flightEntity= FlightConverter.toEntity(flight);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(flightEntity);
        session.getTransaction().commit();
        return flightEntity.getId();
    }

    @Override
    public Flight getFlightById(int flightId) {
        Flight flight = null;
        final Session session = HibernateUtil.getSession();
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
        List <Flight> flights= new ArrayList<Flight>();
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from FlightEntity where actFl=:paramActFl");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List <FlightEntity> flightEntityList=(List <FlightEntity>)query.list();
        for (FlightEntity f:flightEntityList) {
            flights.add(FlightConverter.fromEntity(f));
        }
        return flights;
    }
    public List<Flight> getListFlight(Page page) {
        List <Flight> flights= new ArrayList<Flight>();
        final Session session = HibernateUtil.getSession();
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
        List <FlightEntity> flightEntityList=(List <FlightEntity>)query.list();
        for (FlightEntity f:flightEntityList) {
            flights.add(FlightConverter.fromEntity(f));
        }
        return flights;
    }

    @Override
    public int delete(int flightId) {
        Flight flight=getFlightById(flightId);
        flight.setActual(false);
        return save(flight);
    }

    public int getCountOfFlights() {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select count(*) from FlightEntity where actFl=:paramActFl");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT).
                // сущности и коллекции помечаюся как только для чтения
                        setReadOnly(true);
        long count=(Long) query.getSingleResult();
        return (int) count;
    }
}
