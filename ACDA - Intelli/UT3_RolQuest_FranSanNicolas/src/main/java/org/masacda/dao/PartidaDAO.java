package org.masacda.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.masacda.connection.HibernateUtil;
import org.masacda.model.Partida;

import java.util.List;

public class PartidaDAO {
    public void save(Partida partida) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.persist(partida);      // Inserta el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public Partida findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Partida.class, id);
        }
    }

    public List<Partida> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Partida", Partida.class).list();
        }
    }

    public void update(Partida partida) {
        Transaction tx = null; // Control de la transacción
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Inicia la transacción
            session.merge(partida);        // Modifica el registro
            tx.commit();                     // Confirma los cambios
        } catch (Exception e) {
            if (tx != null) tx.rollback();   // Deshace si hubo error
            e.printStackTrace();
        }
    }

    public void delete(Partida partida) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(partida);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Partida> obtenerConMisiones(int numMisiones){
        String hql = "FROM Partida p WHERE size(p.misiones) > :num";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Partida> query = session.createQuery(hql, Partida.class);
            query.setParameter("num", numMisiones);
            return query.list();
        }
    }
}
