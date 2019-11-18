package com.lesson3.repository;

import com.lesson3.model.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class FileDAO implements IFileDAO {
    private static SessionFactory sessionFactory;


    @Override
    public File save(File file) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(file);
            transaction.commit();
            return file;

        } catch (HibernateException e) {
            System.err.println("Save object: " + file + " is failed");
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
    public File update(File file) {
        return null;
    }

    @Override
    public File delete(long id) {
        return null;
    }

    @Override
    public File findById(long id) {
        return null;
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
