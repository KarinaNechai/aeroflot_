package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.entity.RoleEntity;
import com.github.nechai.aeroflot.model.Role;

import java.util.HashMap;
import java.util.Map;

public class RoleConverter {
    static private Map<Role, Integer> mapRole = new HashMap<>();

    static {
        init();
    }

    public static void init() {
 /*       final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from RoleEntity where code=:paramCode and actFl=:paramActFl");
        query.setParameter("paramCode", Role.class.getSimpleName());
        query.setParameter("paramActFl",1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List<RoleEntity> listRoleEntity = query.list();
        for (RoleEntity pr : listRoleEntity
        ) {
            mapRole.put(Role.valueOf(pr.getvalue()), pr.getId());
        }*/
    }

    public static RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        }
        final RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode(Role.class.getSimpleName());
        roleEntity.setValue(role.name());
        if (mapRole.size() == 0) {
           init();
        }
        roleEntity.setId(mapRole.get(role));
        roleEntity.setActFl(1);
        return roleEntity;
    }

    public static Role fromEntity(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }
        return Role.valueOf(roleEntity.getvalue());
    }
}
