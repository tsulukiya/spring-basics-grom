package com.lesson3.repository;

import com.lesson3.model.File;
import com.lesson3.model.Storage;

import java.util.List;
import java.util.Set;

public interface FileDAO extends Check {

    File save(File file);

    File update(File file);

    File delete(long id);

    File findById(long id);


    File put(Storage storage, File file);


    File delete(Storage storage, File file);


    List<File> transferAll(Storage storageFrom, Storage storageTo);


    File transferFile(Storage storageFrom, Storage storageTo, long id);
}
