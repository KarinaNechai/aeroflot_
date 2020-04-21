package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.IWorkerDao;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDao implements IWorkerDao {
    private static volatile WorkerDao instance;

    private WorkerDao() {
    }

    public static WorkerDao getInstance() {
        WorkerDao localInstance = instance;
        if (localInstance == null) {
            synchronized (WorkerDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new WorkerDao();
                }
            }
        }
        return localInstance;
    }

    public boolean deleteFromBaseForTests(Worker worker) {
        boolean result;
        int workerId = getId(worker);
        String str = "delete from myapp.worker where workerid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement rs = connection.prepareStatement(str);
            rs.setInt(1, workerId);
            result = (rs.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private boolean isExist (Worker worker){
        try {
            String str="select * from myapp.worker t where t.workerid=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, worker.getWorkerid());
            ResultSet rs = ps.executeQuery();
            return (rs.next()) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private int getProfessionId (Profession profession){
        int professionId=-1;
        try {
            String str="select  t.clasid from myapp.classifier t where  t.clascode=? AND t.clasval=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
           ps.setString(1,"profession");
            ps.setString(2, profession.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                professionId=rs.getInt("clasid");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return professionId;
    }
   public int getId (Worker worker){
        try {
            String str="select t.workerid from myapp.worker t where t.workersurname=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, worker.getWorkerSurname() != null ? worker.getWorkerSurname() : "");
            ResultSet rs = ps.executeQuery();
            return (rs.next()?rs.getInt("workerid"):-1) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Worker getWorkerById(int workerId) {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "select t.*,c.clasval from myapp.worker t, myapp.classifier c where t.actfl=1 and t.workerprofession=c.clasid and t.workerid=?"
            );
            ps.setInt(1, workerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final Worker worker = new Worker(
                            rs.getInt("workerId"),
                            rs.getString("workersurname"),
                            rs.getString("workerfirstname"),
                            rs.getString("workerpatronomic"),
                            Profession.valueOf(rs.getString("clasval")),
                            (rs.getInt("actfl") == 1)
                );
                return worker;
            } else {
                return null;
            }

            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }

    @Override
    public boolean insert(Worker worker) {
        String str = "INSERT INTO myapp.worker (workersurname,workerfirstname,actfl,workerpatronomic,workerprofession) VALUES (?,?,?,?,?)";
        try {
             int professionId = getProfessionId(worker.getProfession());
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, worker.getWorkerSurname() != null ? worker.getWorkerSurname() : "");
            ps.setString(2,  worker.getWorkeFirstname()!= null ?worker.getWorkeFirstname() : "");
            ps.setInt(3,worker.isActual()?1:0);
            ps.setString(4,worker.getWorkerPatronomic()!= null ?worker.getWorkerPatronomic(): "");
            ps.setInt(5,professionId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Worker worker){
        if (worker==null) return false;

         int professionId=getProfessionId(worker.getProfession());
        String str= "update myapp.worker set workersurname=?, workerfirstname=?,actfl=?,workerpatronomic=?,workerprofession=? where workerid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, worker.getWorkerSurname() != null ? worker.getWorkerSurname() : "");
            ps.setString(2,  worker.getWorkeFirstname()!= null ?worker.getWorkeFirstname() : "");
            ps.setInt(3,worker.isActual()?1:0);
            ps.setString(4,worker.getWorkerPatronomic()!= null ?worker.getWorkerPatronomic(): "");
            ps.setInt(5,professionId);
            ps.setInt(6,worker.getWorkerid());
            return ps.executeUpdate() == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Worker worker) {
        if (!isExist(worker) ) return true;
        worker.setActual(false);
        return update(worker);
    }

    @Override
    public boolean delete(int workerId) {
        Worker worker=getWorkerById(workerId);
        worker.setActual(false);
        return update(worker);
    }

    @Override
    public List<Worker> getWorkersOfSystem() {
        List <Worker> workers=new ArrayList<>();
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select t.*,c.clasval from myapp.worker t," +
                    " myapp.classifier c where t.actfl=1 and t.workerprofession=c.clasid;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String pr=rs.getString("clasval");
                final Worker worker = new Worker(
                        rs.getInt("workerId"),
                        rs.getString("workersurname"),
                        rs.getString("workerfirstname"),
                        rs.getString("workerpatronomic"),
                        Profession.valueOf(rs.getString("clasval")),
                        (rs.getInt("actfl") == 1)
                       );
                workers.add(worker);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return workers;
    }

    @Override
    public List<Worker> getWorkersByProfession(Profession profession) {
        int professionId=getProfessionId(profession);
        List <Worker> workers=new ArrayList<>();
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("select t.*,c.clasval from myapp.worker t" +
                    ",myapp.classifier c where (t.workerprofession=? and t.actfl=1) and " +
                    "t.workerprofession=c.clasid");
            ps.setInt(1, professionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Worker worker = new Worker(
                        rs.getInt("workerId"),
                        rs.getString("workersurname"),
                        rs.getString("workerfirstname"),
                        rs.getString("workerpatronomic"),
                        profession,
                        (rs.getInt("actfl") == 1));
                        workers.add(worker);
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return workers;
        }
}
