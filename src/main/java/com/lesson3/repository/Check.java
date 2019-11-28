package com.lesson3.repository;

import com.lesson3.model.File;
import com.lesson3.model.Storage;

public interface Check {

    public void checkFormatSupported(File file, Storage storage);

    public void checkMaxSize(File file, Storage storage);
}
