package com.lesson3.service;

import com.lesson3.model.File;
import com.lesson3.repository.IFileDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class FileService implements IFileService {
    @Autowired
    private IFileDAO iFileDAO;
    @Override
    public File save(File file) {
        return iFileDAO.save(file);
    }

    @Override
    public File update(File file) {
        return iFileDAO.update(file);
    }

    @Override
    public File delete(long id) {
        return iFileDAO.delete(id);
    }

    @Override
    public File findById(long id) {
        return iFileDAO.findById(id);
    }
}
