package com.lesson3.controller;

import com.lesson3.model.File;
import com.lesson3.model.Storage;
import com.lesson3.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class FileController {
    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFile", produces = "application/json")
    public @ResponseBody
    File save(File file) {
        return fileService.save(file);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateFile", produces = "application/json")
    public @ResponseBody
    File update(File file) {
        return fileService.update(file);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteFile", produces = "application/json")
    public @ResponseBody
    File delete(long id) {
        return fileService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findFile", produces = "application/json")
    public @ResponseBody
    File findById(long id) {
        return fileService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/putFileToStorage", produces = "application/json")
    public @ResponseBody
    File put(long idStorage, long idFile) {
        File file = new File(idFile);
        Storage storage = new Storage(idStorage);
        return fileService.put(storage, file);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteFileFromStorage", produces = "application/json")
    public @ResponseBody
    File delete(long idStorage, long idFile) {
        File file = new File(idFile);
        Storage storage = new Storage(idStorage);
        return fileService.delete(storage, file);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferAllFiles", produces = "application/json")
    public @ResponseBody
    List <File> transferAll(long idStorageFrom, long idStorageTo) {
        Storage storageFrom = new Storage(idStorageFrom);
        Storage storageTo = new Storage(idStorageTo);
        return fileService.transferAll(storageFrom, storageTo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferFile", produces = "application/json")
    public @ResponseBody
    File transferFile(long idStorageFrom, long idStorageTo, long id) {
        Storage storageFrom = new Storage(idStorageFrom);
        Storage storageTo = new Storage(idStorageTo);
        return fileService.transferFile(storageFrom, storageTo, id);
    }
}
