package com.jwt.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Prenotazione;
import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.bean.Veicolo;
import com.jwt.hibernate.util.HibernateUtil;

public class VeicoloDAO {

    public List<Veicolo> getVeicoli() throws Exception {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;
        List<Veicolo> veicoli = new ArrayList<>();
        try {
            session = sf.openSession();
            tx = session.beginTransaction();

            String hql = "FROM Veicolo";
            Query query = session.createQuery(hql);
            veicoli = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
        return veicoli;
    }

    public Veicolo getVeicoloById(int veicoloId) throws Exception {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;
        Veicolo veicolo = null;

        try {
            session = sf.openSession();
            tx = session.beginTransaction();

            String hql = "FROM Veicolo WHERE veicolo_id = :veicoloId";
            Query query = session.createQuery(hql);
            query.setParameter("veicoloId", veicoloId);

            veicolo = (Veicolo) query.uniqueResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return veicolo;
    }

    public void bookVeicolo(int userId, int veicoloId, String startDate, String endDate) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            User user = (User) session.get(User.class, userId);
            Veicolo veicolo = (Veicolo) session.get(Veicolo.class, veicoloId);

            if (veicolo == null || user == null) {
                throw new Exception("Utente o veicolo non trovati.");
            }

            Prenotazione prenotazione = new Prenotazione(user, veicolo, java.time.LocalDate.now().toString(), startDate, endDate);

            session.save(prenotazione); 

            veicolo.setDisponibilita("Non Disponibile");
            session.update(veicolo);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new Exception("Errore durante la prenotazione del veicolo e l'aggiornamento della disponibilità.", e);
        } finally {
            if (session != null) session.close();
        }
    }
}
