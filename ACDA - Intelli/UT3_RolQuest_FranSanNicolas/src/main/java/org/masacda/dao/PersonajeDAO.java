package org.masacda.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.masacda.connection.HibernateUtil;
import org.masacda.model.Personaje;
import java.util.List;

public class PersonajeDAO {

    public void save(Personaje personaje) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.persist(personaje);      // Inserta el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public Personaje findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Personaje.class, id);
        }
    }

    public List<Personaje> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Personaje", Personaje.class).list();
        }
    }

    public void update(Personaje personaje) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.merge(personaje);        // Modifica el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public void delete(Personaje personaje) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(personaje);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
