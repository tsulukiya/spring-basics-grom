package com.lesson3.repository;

import com.lesson3.model.File;
import com.lesson3.model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.SizeLimitExceededException;
import java.io.IOException;
import java.util.*;


@Repository
public class FileDAOImpl implements FileDAO {
    private static SessionFactory sessionFactory;
    private static final String sqlQueryFindById = "from File where id =:code";
    private static final String sqlQueryFindAllFileFromStorage = "from File where storage =:code";
    private StorageDAO storageDAO;

    @Autowired
    public FileDAOImpl(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

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
        File file = null;
        try {
            session = createSessionFactory().openSession();
            Query query = session.createQuery(sqlQueryFindById);
            query.setParameter("code", id);
            for (Object o : query.list()) {
                file = (File) o;
            }
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
        file = findById(file.getId());
        file.setStorage(storageDAO.findById(storage.getId()));
        update(file);
        return file;
    }

    @Override
    public File delete(Storage storage, File file) {
        file = findById(file.getId());
        file.setStorage(null);
        update(file);
        return file;
    }

    @Override
    public List<File> transferAll(Storage storageFrom, Storage storageTo) {
        Session session = null;
        storageFrom = storageDAO.findById(storageFrom.getId());
        List<File> fileList = new ArrayList<>();
        try {
            session = createSessionFactory().openSession();
            Query query = session.createQuery(sqlQueryFindAllFileFromStorage);
            query.setParameter("code", storageFrom);
            for (Object o : query.list()) {
                fileList.add((File) o);
            }
            for (File file : fileList) {
                file.setStorage(storageDAO.findById(storageTo.getId()));
                update(file);
            }
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

    @Override
    public void checkFormatSupported(File file, Storage storage) {
        file = findById(file.getId());
        storage = storageDAO.findById(storage.getId());
        int count = 0;
        String[] masFormat = storage.getFormatsSupported().split(",");
        for (String s : masFormat) {
            if (s.equals(file.getFormat()))
                count++;
        }
        if (count > 0) {
            System.out.println("Storage is supported format this file...");
        } else {
            throw new IllegalArgumentException("Storage" + storage + "is not supported format this file...");
        }
    }

    @Override
    public void checkMaxSize(File file, Storage storage) {
        file = findById(file.getId());
        storage = storageDAO.findById(storage.getId());
        long fileSize = file.getSize();
        long storageSize = storage.getStorageSize();
        long sizeFileFromStorage = 0;

        Session session = null;
        List<File> fileList = new ArrayList<>();
        try {
            session = createSessionFactory().openSession();
            Query query = session.createQuery(sqlQueryFindAllFileFromStorage);
            query.setParameter("code", storage);
            for (Object o : query.list()) {
                fileList.add((File) o);
            }

            for (File file1 : fileList) {
                sizeFileFromStorage += file1.getSize();
            }

            if ((storageSize - sizeFileFromStorage) < fileSize) {
                throw new IllegalArgumentException("Size this file is very big for this storage");
            }

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
}
