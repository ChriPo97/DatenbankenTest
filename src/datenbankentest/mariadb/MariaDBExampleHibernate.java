/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datenbankentest.mariadb;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author DatenbankenTest
 */
public class MariaDBExampleHibernate {
    
    public static void main(String[] args) throws HibernateException {
        
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        users users = new users("TestHibernate", "TestHibernate");

        session.save(users);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        
    }
    
}
