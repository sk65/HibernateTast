package com.testHibernate.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory managerFactory;

    static {
        managerFactory = Persistence.createEntityManagerFactory("JPA");
    }

    public static EntityManagerFactory getManagerFactory() {
        return managerFactory;
    }
}
