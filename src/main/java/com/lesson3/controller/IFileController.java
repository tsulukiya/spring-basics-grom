package com.lesson3.controller;

import com.lesson3.model.File;

public interface IFileController {
    File save(File file);

    File update(File file);

    File delete(long id);

    File findById(long id);
}
