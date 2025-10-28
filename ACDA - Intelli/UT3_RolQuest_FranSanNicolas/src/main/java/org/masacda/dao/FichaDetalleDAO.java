package org.masacda.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.masacda.connection.HibernateUtil;
import org.masacda.model.FichaDetalle;

import java.util.List;

public class FichaDetalleDAO {
    public void save(FichaDetalle fd) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.persist(fd);      // Inserta el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public FichaDetalle findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(FichaDetalle.class, id);
        }
    }

    public List<FichaDetalle> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from FichaDetalle", FichaDetalle.class).list();
        }
    }

    public void update(FichaDetalle fd) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.merge(fd);        // Modifica el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public void delete(FichaDetalle fd) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(fd);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
