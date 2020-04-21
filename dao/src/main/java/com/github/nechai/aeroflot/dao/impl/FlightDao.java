package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.IFlightDao;
import com.github.nechai.aeroflot.model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


    @Override
    public boolean insert(int airportFrom, int airportTo, Date date) {
            String str= "INSERT INTO myapp.flight (airportfrom,airportto,flightdate) VALUES (?,?,?)";
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String s=format.format(date);
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

    }

    @Override
    public Flight getFlightById(int flightId) {
        Flight flight = null;
        try {
            String str="select t.* from myapp.flight t where t.flightid=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, flightId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flight = new Flight(
                        flightId,
                        rs.getInt("airportfrom"),
                        rs.getInt("airportto"),
                        rs.getInt("crewid"),
                        rs.getInt("planeid"),
                        rs.getInt("flightrange"),
                        rs.getInt("amountpassengers"),
                        rs.getDate("flightdate"),
                        (rs.getInt("actfl") == 1)
                );
            }
            }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return flight;
    }
    public List<Flight> getListFlight() {
        List <Flight> flights= new ArrayList<Flight>();
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select t.* from myapp.flight t  where t.actfl=?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getInt("flightid"),
                        rs.getInt("airportfrom"),
                        rs.getInt("airportto"),
                        rs.getInt("crewid"),
                        rs.getInt("planeid"),
                        rs.getInt("flightrange"),
                        rs.getInt("amountpassengers"),
                        rs.getDate("flightdate"),
                        rs.getInt("actfl") == 1);
                        flights.add(flight);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return flights;
    }

    @Override
    public boolean delete(int flightId) {
        return false;
    }
}
