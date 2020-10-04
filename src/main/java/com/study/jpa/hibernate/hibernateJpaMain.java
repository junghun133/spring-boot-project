package com.study.jpa.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class hibernateJpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orm");
        EntityManager em = emf.createEntityManager();

        em.close();
        emf.close();
    }
}
