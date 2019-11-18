package com.lesson3.service;

import com.lesson3.model.File;
import org.springframework.beans.factory.annotation.Autowired;

public class FileServiceImpl implements FileService {
    @Autowired
    private FileDAO fileDAO;
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
}
