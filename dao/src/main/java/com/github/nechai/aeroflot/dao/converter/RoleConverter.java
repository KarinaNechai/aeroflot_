package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.entity.ProfessionEntity;
import com.github.nechai.aeroflot.dao.entity.RoleEntity;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Role;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleConverter {
    static private Map<Role, Integer> mapRole = new HashMap<>();

    static {
        init();
    }

    public static void init() {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from RoleEntity where code=:paramCode");
        query.setParameter("paramCode", Role.class.getName());
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
        }
    }

    public static RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        }
        final RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode(Role.class.getName());
        roleEntity.setValue(role.name());
        if (mapRole.size() > 0) {
            roleEntity.setId(mapRole.get(role));
        }
        return roleEntity;
    }

    public static Role fromEntity(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }
        return Role.valueOf(roleEntity.getvalue());
    }
}
