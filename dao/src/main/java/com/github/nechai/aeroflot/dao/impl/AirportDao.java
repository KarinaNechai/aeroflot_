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
    public int insert(Airport airport) {
        AirportEntity airportEntity = AirportConverter.toEntity(airport);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(airportEntity);
        session.getTransaction().commit();
        return airportEntity.getId();
    }

 /*   private int getId(Airport airport) {
        try {
            String str = "select t.airportid from myapp.airport t where t.airportname=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, airport.getName() != null ? airport.getName() : "");
            ResultSet rs = ps.executeQuery();
            return (rs.next() ? rs.getInt("airportid") : -1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/

    @Override
    public boolean update(Airport airport){
     AirportEntity airportEntity = AirportConverter.toEntity(airport);
     final Session session = HibernateUtil.getSession();
     session.beginTransaction();
     session.saveOrUpdate(airportEntity);
     session.getTransaction().commit();
     return true;
}
  /*  @Override
    public boolean update(Airport airport) {
        if (airport==null) return false;
        int airportId=airport.getId();
        int actFl=airport.isActual()?1:0;
        if (airportId==-1 ) return false;
        String str= "update myapp.airport set airportname=?, actfl=? where airportid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, airport.getName() != null ? airport.getName() : "");
            ps.setInt(2, actFl);
            ps.setInt(3, airportId);
            if (1 != ps.executeUpdate()) {
                return false;
            } else {
                boolean res=true;
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
*/
    @Override
    public boolean delete(Airport airport) {
        airport.setActual(false);
        return update(airport);
    }

    @Override
    public boolean delete(int airportId) {
        return delete(getAirportById(airportId));
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
 /*   public Airport getAirportById(int airportId) {
         try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select t.* from myapp.airport t  where t.airportid=?");
            ps.setInt(1, airportId);
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                final Airport airport = new Airport(
                        rs.getInt("airportid"),
                        rs.getString("airportname"),
                        rs.getInt("actfl") == 1);
                return airport;
            }else             {return null;}

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/
}
