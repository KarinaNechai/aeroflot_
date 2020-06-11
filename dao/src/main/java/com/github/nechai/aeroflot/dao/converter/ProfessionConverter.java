package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.entity.ProfessionEntity;
import com.github.nechai.aeroflot.model.Profession;

import java.util.HashMap;
import java.util.Map;

public class ProfessionConverter {
    static private Map<Profession,Integer> mapPr=new HashMap<>();
    static {
        init();
    }
    public static void init()
    {
 /*       final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from ProfessionEntity where code=:paramId and actFl=:paramActFl");
        query.setParameter("paramId", Profession.class.getSimpleName());

        String str=Profession.class.getSimpleName();
        query.setParameter("paramActFl",1);
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
        }*/
    }

    public static ProfessionEntity toEntity(Profession profession){
        if (profession==null){
        return null;
        }
        final ProfessionEntity professionEntity=new  ProfessionEntity();
        professionEntity.setCode(Profession.class.getSimpleName());
        professionEntity.setValue(profession.name());
        if (mapPr.size()==0) {
            init();
        }
        professionEntity.setId(mapPr.get(profession));
        professionEntity.setActFl(1);
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
