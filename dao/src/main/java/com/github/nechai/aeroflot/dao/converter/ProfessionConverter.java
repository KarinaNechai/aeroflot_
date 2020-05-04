package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.entity.ProfessionEntity;
import com.github.nechai.aeroflot.model.Profession;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfessionConverter {
    static private Map<Profession,Integer> mapPr=new HashMap<>();
    static {
        init();
    }
    public static void init()
    {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from ProfessionEntity where code=:paramId");
        query.setParameter("paramId", "profession");
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List<ProfessionEntity> listProfessionEntity=(List<ProfessionEntity>)query.list();
        for (ProfessionEntity pr:listProfessionEntity
        ) {
            mapPr.put(Profession.valueOf(pr.getvalue()),pr.getId());
        }
    }

    public static ProfessionEntity toEntity(Profession profession){
        if (profession==null){
        return null;
        }
        final ProfessionEntity professionEntity=new  ProfessionEntity();
        professionEntity.setCode("profession");
        professionEntity.setValue(profession.name());
        if (mapPr.size()>0) {
            professionEntity.setId(mapPr.get(profession));
        }
        return  professionEntity;
    }

    public static Profession fromEntity(ProfessionEntity professionEntity){
        if (professionEntity==null){
            return null;
        }
        Profession profession=Profession.valueOf(professionEntity.getvalue());
        return profession;
    }
}
