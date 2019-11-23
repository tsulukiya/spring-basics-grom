package com.lesson3.service;

import com.lesson3.model.File;
import com.lesson3.model.Storage;
import com.lesson3.repository.StorageDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDAO storageDAO;

    @Override
    public Storage save(Storage storage) {
        return storageDAO.save(storage);
    }

    @Override
    public Storage update(Storage storage) {
        return storageDAO.update(storage);
    }

    @Override
    public Storage delete(long id) {
        return storageDAO.delete(id);
    }

    @Override
    public Storage findById(long id) {
        return storageDAO.findById(id);
    }
}
