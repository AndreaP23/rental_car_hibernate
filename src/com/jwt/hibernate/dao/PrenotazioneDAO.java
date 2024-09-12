package com.jwt.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jwt.hibernate.bean.Prenotazione;
import com.jwt.hibernate.util.HibernateUtil;

public class PrenotazioneDAO {
	
	public List<Prenotazione> getPrenotazione() throws Exception{
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;
        List<Prenotazione> prenotazioni = new ArrayList<>();
        try {
        	session = sf.openSession();
        	tx = session.beginTransaction();
        	String hql = "from Prenotazione";
        	Query query = session.createQuery(hql);
        	prenotazioni = query.list();
        	
        	tx.commit();
        }catch(HibernateException e) {
        	if(tx !=null) tx.rollback();
        	System.out.println("Error " + e.getMessage());
        }finally {
        	if(session != null) session.close();
        }
        return prenotazioni;
	}
}
