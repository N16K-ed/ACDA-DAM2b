package org.masacda.app;

import org.hibernate.Session;
import org.masacda.connection.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        System.out.println("Hibernate conectado correctamente a la base de datos ACDA_UT3.");

        session.getTransaction().commit();
        session.close();
        HibernateUtil.closeSessionFactory();
    }
}