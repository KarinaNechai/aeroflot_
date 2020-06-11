package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.IPlaneDao;
import com.github.nechai.aeroflot.dao.converter.PlaneConverter;
import com.github.nechai.aeroflot.dao.entity.PlaneEntity;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Plane;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaneDao implements IPlaneDao {
    private final SessionFactory factory;
    public PlaneDao(SessionFactory factory) {
        this.factory=factory;
    }

     public int save(Plane plane){
        PlaneEntity planeEntity = PlaneConverter.toEntity(plane);
        final Session session = factory.getCurrentSession();
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
        final Session session =factory.getCurrentSession();
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
        final Session session = factory.getCurrentSession();
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
        final Session session =factory.getCurrentSession();
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
        final Session session =factory.getCurrentSession();
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
