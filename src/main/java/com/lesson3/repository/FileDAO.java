package com.lesson3.repository;

import com.lesson3.model.File;

public interface FileDAO {

    File save(File file);

    File update(File file);

    File delete(long id);

    File findById(long id);
}
