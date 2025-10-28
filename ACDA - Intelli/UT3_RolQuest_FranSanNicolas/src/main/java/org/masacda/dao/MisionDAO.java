package org.masacda.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.masacda.connection.HibernateUtil;
import org.masacda.model.Mision;
import java.util.List;

public class MisionDAO {

    public void save(Mision mision) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.persist(mision);      // Inserta el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public Mision findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Mision.class, id);
        }
    }

    public List<Mision> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Mision", Mision.class).list();
        }
    }

    public void update(Mision mision) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.merge(mision);        // Modifica el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public void delete(Mision mision) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(mision);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
