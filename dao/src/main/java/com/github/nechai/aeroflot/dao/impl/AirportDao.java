package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.IAirportDao;
import com.github.nechai.aeroflot.model.Airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public boolean insert(Airport airport) {
        if (airport==null) return false;
        String str= "INSERT INTO myapp.airport (airportname,actfl) VALUES (?,?)";
        int actFl=airport.isActual()?1:0;
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, airport.getName());
            ps.setInt(2, actFl);
            if(ps.executeUpdate() == 1){
                boolean res=true;
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e)
        {throw new RuntimeException(e);
        }
    }
    private int getId (Airport airport){
    try {
            String str="select t.airportid from myapp.airport t where t.airportname=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, airport.getName() != null ? airport.getName() : "");
            ResultSet rs = ps.executeQuery();
            return (rs.next()?rs.getInt("airportid"):-1) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public boolean delete(Airport airport) {
        int airportId=getId(airport);
        if (airportId==-1 ) return true;
        airport.setActual(false);
        return update(airport);
    }

    @Override
    public boolean delete(int airportId) {
        String str= "update myapp.airport set actfl=0 where airportid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
             ps.setInt(1, airportId);
            return (1 != ps.executeUpdate())?false:true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Airport> getListAirport() {
         List <Airport> airports=new ArrayList<>();
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select t.* from myapp.airport t  where t.actfl=?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Airport airport = new Airport(
                        rs.getInt("airportid"),
                        rs.getString("airportname"),
                        rs.getInt("actfl")==1?true:false);
                airports.add(airport);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return airports;
    }
    public Airport getAirportById(int airportId) {
         try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select t.* from myapp.airport t  where t.airportid=?");
            ps.setInt(1, airportId);
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                final Airport airport = new Airport(
                        rs.getInt("airportid"),
                        rs.getString("airportname"),
                        rs.getInt("actfl")==1?true:false);
                return airport;
            }else             {return null;}

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
