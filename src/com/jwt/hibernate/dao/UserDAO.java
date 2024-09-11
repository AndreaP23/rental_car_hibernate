package com.jwt.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.jwt.hibernate.bean.User;
import com.jwt.hibernate.util.HibernateUtil;

public class UserDAO {

    public User getUser(String email, String password) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;
        User user = null;
        try {
            session = sf.openSession();
            tx = session.beginTransaction();

            String hql = "FROM User WHERE email = :email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            List<User> results = query.list();

            if (results.size() == 1) {
                user = results.get(0);
                System.out.println("Retrieved user with email: " + user.getEmail());
                System.out.println("Stored hashed password: " + user.getPassword());
                System.out.println("Provided password: " + password);

                if (BCrypt.checkpw(password, user.getPassword())) {
                    tx.commit();
                    return user;
                } else {
                    tx.rollback();
                    return null;
                }
            } else {
                tx.rollback();
                return null; 
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    public void addUserDetails(String nome, String cognome, String password, String email,
            String telefono, String dataNascita) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User user = new User();
            user.setNome(nome);
            user.setCognome(cognome);
            user.setPassword(hashedPassword); 
            user.setEmail(email);
            user.setTelefono(telefono);
            user.setDataNascita(dataNascita);
            user.setRuolo("Customer");
            
            session.save(user);
            transaction.commit();
            System.out.println("Details Added");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    
    public List<User> getUsers() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction tx = null;
        List<User> users = new ArrayList<>();
        try {
            session = sf.openSession();
            tx = session.beginTransaction();

            String hql = "FROM User";
            Query query = session.createQuery(hql);
            users = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
        return users;
    }


}
