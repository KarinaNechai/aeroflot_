package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.model.Crew;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrewDao {
    private static volatile CrewDao instance;
    private CrewDao() {
    }

    public static CrewDao getInstance() {
        CrewDao localInstance = instance;
        if (localInstance == null) {
            synchronized (CrewDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CrewDao();
                }
            }
        }
        return localInstance;
    }


   public boolean insertWorkerToCrew(int crewId,int workerId) {
        String str= "INSERT INTO myapp.crew_worker (crewid,workerid) VALUES (?,?)";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, crewId);
            ps.setInt(2, workerId);
             return (ps.executeUpdate() == 1);
        }catch (SQLException e)
        {throw new RuntimeException(e);
        }
    }

    public int getCrewIdByFlightId(int flightId) {
        String str= "SELECT t.crewid from myapp.crew t where t.flightid=? ";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, flightId);
            ResultSet rs = ps.executeQuery();
            return (rs.next()? rs.getInt("crewid"):-1);
        }catch (SQLException e)
        {throw new RuntimeException(e);
        }
    }


    public boolean insertCrewByFlightId (int flightId) {
       String str= "INSERT INTO myapp.crew (flightid,actfl) VALUES (?,?)";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, flightId);
            ps.setInt(2, 1);
            int resultRq=ps.executeUpdate();
             return (ps.executeUpdate() == 1);
        }catch (SQLException e)
        {throw new RuntimeException(e);
        }
    }

    public List <Worker> getCrewListWorkerById(int crewId){
        List <Worker> listWorker=new ArrayList<>();
        try {
            String str="select t1.*,t2.clasval from myapp.crew_worker t join myapp.worker t1 on t.workerid=t1.workerid " +
                    "join myapp.classifier t2 on t1.workerprofession=t2.clasid where t.crewid=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, crewId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                final Worker worker = new Worker(
                        rs.getInt("workerId"),
                        rs.getString("workersurname"),
                        rs.getString("workerfirstname"),
                        rs.getString("workerpatronomic"),
                        Profession.valueOf(rs.getString("clasval")),
                        (rs.getInt("actfl") == 1)
                );
                listWorker.add(worker);
            }
            return listWorker ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Crew getCrewById(int crewId){
        Crew crew = null;
        try {
            String str="select t.* from myapp.crew t where t.crewid=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, crewId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                List <Worker> listWorkers=getCrewListWorkerById(crewId);
                crew=new Crew (
                         crewId,
                         rs.getInt("flightid"),
                         listWorkers,
                        (rs.getInt("actfl") == 1)
                );
            }
             return  crew;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
