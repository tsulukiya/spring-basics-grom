package com.lesson3.service;

import com.lesson3.model.Storage;


public interface StorageService {

    Storage save(Storage storage);

    Storage update(Storage storage);

    Storage delete(long id);

    Storage findById(long id);
}
