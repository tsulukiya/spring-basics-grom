package com.lesson3.repository;

import com.lesson3.model.File;
import com.lesson3.model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class FileDAOImpl implements FileDAO {
    private static SessionFactory sessionFactory;
    private String sqlQueryFindById = "from File where id =:code";
    private String sqlQueryFindAllFileFromStorage = "from File where storage =:code";


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
        Transaction transaction = null;
        Session session = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.update(file);
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
    public File delete(long id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            File file = findById(id);
            session.delete(file);
            tr.commit();
            return file;
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
    public File findById(long id) {

        Session session = null;
        Transaction tr;
        File file = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Query query = session.createQuery(sqlQueryFindById);
            query.setParameter("code", id);
            for (Object o : query.list()) {
                file = (File) o;
            }
            tr.commit();
            return file;
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

    @Override
    public File put(Storage storage, File file) {
        file.setStorage(storage);
        update(file);
        return file;
    }

    @Override
    public File delete(Storage storage, File file) {
        file.setStorage(null);
        update(file);
        return file;
    }

    @Override
    public List<File> transferAll(Storage storageFrom, Storage storageTo) {
        Session session = null;
        Transaction tr;
        List<File> fileList = new ArrayList<>();
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Query query = session.createQuery(sqlQueryFindAllFileFromStorage);
            query.setParameter("code", storageFrom);
            for (Object o : query.list()) {
                fileList.add((File) o);
            }
            for (File file : fileList) {
                file.setStorage(storageTo);
                update(file);
            }
            tr.commit();
            return fileList;

        } catch (HibernateException e) {
            System.err.println("TransferAll is failed");
            System.err.println(e.getMessage());
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public File transferFile(Storage storageFrom, Storage storageTo, long id) {
        File file = findById(id);
        if (file.getStorage().equals(storageFrom)) {
            file.setStorage(storageTo);
            update(file);
        }
        return file;
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
