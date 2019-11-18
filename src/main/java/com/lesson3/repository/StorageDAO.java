package com.lesson3.repository;

import com.lesson3.model.Storage;

public interface StorageDAO {
    Storage save(Storage storage);

    Storage update(Storage storage);

    Storage delete(long id);

    Storage findById(long id);
}
