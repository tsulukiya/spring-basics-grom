package com.lesson3.service;

import com.lesson3.model.File;

public interface FileService {
    File save(File file);

    File update(File file);

    File delete(long id);

    File findById(long id);
}
