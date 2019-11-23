package com.lesson3.repository;

import com.lesson3.model.File;
import com.lesson3.model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class StorageDAOImpl implements StorageDAO {
    private static SessionFactory sessionFactory;
    public String sqlQueryFindById = "from Storage where id =:code";

    @Override
    public Storage save(Storage storage) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(storage);
            transaction.commit();
            return storage;

        } catch (HibernateException e) {
            System.err.println("Save object: " + storage + " is failed");
            System.err.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Storage update(Storage storage) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update(storage);
            transaction.commit();
            return storage;

        } catch (HibernateException e) {
            System.err.println("Update object: " + storage + " is failed");
            System.err.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Storage delete(long id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Storage storage = findById(id);
            session.delete(storage);
            tr.commit();
            return storage;
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

    @Override
    public Storage findById(long id) {
        Session session = null;
        Transaction tr;
        Storage storage = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Query query = session.createQuery(sqlQueryFindById);
            query.setParameter("code", id);
            for (Object o : query.list()) {
                storage = (Storage) o;
            }
            tr.commit();
            return storage;
        } catch (HibernateException e) {
            System.err.println("FindByIdStorage is failed");
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
