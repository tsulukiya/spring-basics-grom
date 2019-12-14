package com.lesson3.service;

import com.lesson3.model.File;
import com.lesson3.model.Storage;
import com.lesson3.repository.FileDAO;
import com.lesson3.repository.FileDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Set;

@Service
public class FileServiceImpl implements FileService {
    private FileDAO fileDAO;

    @Autowired
    public FileServiceImpl(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Override
    public File save(File file) {
        return fileDAO.save(file);
    }

    @Override
    public File update(File file) {
        return fileDAO.update(file);
    }

    @Override
    public File delete(long id) {
        return fileDAO.delete(id);
    }

    @Override
    public File findById(long id) {
        return fileDAO.findById(id);
    }

    @Override
    public File put(Storage storage, File file) {
        fileDAO.checkFormatSupported(file, storage);
        fileDAO.checkMaxSize(file, storage);
        return fileDAO.put(storage, file);
    }

    @Override
    public File delete(Storage storage, File file) {
        return fileDAO.delete(storage, file);
    }

    @Override
    public List<File> transferAll(Storage storageFrom, Storage storageTo) {
        return fileDAO.transferAll(storageFrom, storageTo);
    }

    @Override
    public File transferFile(Storage storageFrom, Storage storageTo, long id) {
        return fileDAO.transferFile(storageFrom, storageTo, id);
    }



}
