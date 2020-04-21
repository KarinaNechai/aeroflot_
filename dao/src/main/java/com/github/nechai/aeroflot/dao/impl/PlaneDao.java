package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.IPlaneDao;
import com.github.nechai.aeroflot.model.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneDao implements IPlaneDao {
    private static volatile PlaneDao instance;
    private PlaneDao() {
    }

    public static PlaneDao getInstance() {
        PlaneDao localInstance = instance;
        if (localInstance == null) {
            synchronized (PlaneDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PlaneDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean insert(Plane plane) {
    String str= "INSERT INTO myapp.plane (planename,capacity,length) VALUES (?,?,?)";
       try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, plane.getPlaneName()!=null?plane.getPlaneName():"");
            ps.setInt(2, plane.getCapacity());
            ps.setInt(3, plane.getRange());
            return (ps.executeUpdate() == 1);
        }catch (SQLException e)
        {throw new RuntimeException(e);
        }
    }
    private int getId (Plane plane){
        try {
            String str="select t.planeid from myapp.plane t where t.planename=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, plane.getPlaneName() != null ? plane.getPlaneName() : "");
            ResultSet rs = ps.executeQuery();
            return (rs.next()?rs.getInt("planeid"):-1) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean update(Plane plane) {
        if (plane==null) return false;
        int planeId=plane.getPlaneId();
    //    int actFl=plane.isActual()?1:0;
    //    if (planeId==-1 ) return false;
         String str= "update myapp.plane set planename=?,capacity=?,length=?,actfl=? where planeid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, plane.getPlaneName() != null ? plane.getPlaneName() : "");
            ps.setInt(2, plane.getCapacity());
            ps.setInt(3, plane.getRange());
            ps.setInt(4,plane.isActual()?1:0);
            ps.setInt(5,planeId);
           return (1 != ps.executeUpdate());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(Plane plane) {
        int planeId=getId(plane);
        if (planeId==-1 ) return true;
        plane.setActual(false);
        return update(plane);
    }
    public Plane getPlaneById(int planeId) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "select t.*  from myapp.plane t where t.actfl=1 and t.planeid=?"
            );
            ps.setInt(1, planeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final Plane plane = new Plane(
                        rs.getInt("planeId"),
                        rs.getString("planename"),
                        rs.getInt("capacity"),
                        rs.getInt("length"),
                        (rs.getInt("actfl") == 1)
                );
                return plane;
            } else {
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int planeId) {
        Plane plane=getPlaneById(planeId);
        plane.setActual(false);
        return update(plane);
    }

    @Override
    public List<Plane> getListPlane() {
        List <Plane> planes= new ArrayList<>();
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select t.* from myapp.plane t  where t.actfl=?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              final Plane plane = new Plane(
                        rs.getInt("planeid"),
                        rs.getString("planename"),
                        rs.getInt("capacity"),
                        rs.getInt("length"),
                        rs.getInt("actfl") == 1);
                        planes.add(plane);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return planes;
    }

}
