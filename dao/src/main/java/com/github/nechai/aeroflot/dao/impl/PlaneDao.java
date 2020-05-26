package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.IPlaneDao;
import com.github.nechai.aeroflot.dao.converter.PlaneConverter;
import com.github.nechai.aeroflot.dao.entity.AirportEntity;
import com.github.nechai.aeroflot.dao.entity.PlaneEntity;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Plane;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

     public int save(Plane plane){
        PlaneEntity planeEntity = PlaneConverter.toEntity(plane);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(planeEntity);
        session.getTransaction().commit();
        return planeEntity.getId();
    }
    @Override
    public int delete(Plane plane) {
        plane.setActual(false);
        return save(plane);
    }
    public Plane getPlaneById(int planeId) {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from PlaneEntity where id=:paramId");
        query.setParameter("paramId", planeId);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        return PlaneConverter.fromEntity((PlaneEntity) query.uniqueResult());
    }

    public int delete(int planeId) {
        Plane plane=getPlaneById(planeId);
        plane.setActual(false);
        return save(plane);
    }

    @Override
    public List<Plane> getListPlane() {
        List <Plane> planes= new ArrayList<>();
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from PlaneEntity where actFl=:paramActFl order by planeName asc");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List <PlaneEntity> planeEntityList=(List <PlaneEntity>)query.list();
        for (PlaneEntity p:planeEntityList) {
            planes.add(PlaneConverter.fromEntity(p));
        }
        return planes;
    }

    public List<Plane> getListPlane(Page page) {
        List <Plane> planes= new ArrayList<>();
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from PlaneEntity where actFl=:paramActFl order by planeName asc");
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        query.setFirstResult(page.getFirst());
        query.setMaxResults(page.getMax());
        List <PlaneEntity> planeEntityList=(List <PlaneEntity>)query.list();
        for (PlaneEntity p:planeEntityList) {
            planes.add(PlaneConverter.fromEntity(p));
        }
        return planes;
    }

    public int getCountOfPlanes() {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select count(*) from PlaneEntity where actFl=:paramActFl order by planeName asc");
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
