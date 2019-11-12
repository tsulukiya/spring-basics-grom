package com.lesson2.part2.ItemDAO;

import com.lesson2.part2.Entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class ItemDAO {
    private static SessionFactory sessionFactory;
    public String sqlQueryFindById = "from Item where id =:code";


    public Item save(Item item) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(item);
            transaction.commit();
            return item;

        } catch (HibernateException e) {
            System.err.println("Save object: " + item + " is failed");
            System.err.println(e.getMessage());
            transaction.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Item update(Item item) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update(item);
            transaction.commit();
            return item;

        } catch (HibernateException e) {
            System.err.println("Save object: " + item + " is failed");
            System.err.println(e.getMessage());
            transaction.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Item delete(long id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Item item = findById(id);
            session.delete(item);
            tr.commit();
            return item;
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
                throw e;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public Item findById(long id) {
        Session session = null;
        Transaction tr;
        Item item = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Query query = session.createQuery(sqlQueryFindById);
            query.setParameter("code", id);
            for (Object o : query.list()) {
                item = (Item) o;
            }
            tr.commit();
            return item;
        } catch (HibernateException e) {
            System.err.println("Find by is failed");
            System.err.println(e.getMessage());
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
