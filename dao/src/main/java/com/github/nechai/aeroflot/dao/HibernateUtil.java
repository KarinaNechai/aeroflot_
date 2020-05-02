package com.github.nechai.aeroflot.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory emFactory;

    public static EntityManager getEntityManager() {
        return getEntityManager("MAIN-UNIT");
    }
    public static EntityManager getEntityManager(String unit) {
        if (emFactory == null) {
            emFactory = Persistence.createEntityManagerFactory(unit);
        }
        return emFactory.createEntityManager();
    }

    public static Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }

    public static void close() {
        emFactory.close();
    }

}
