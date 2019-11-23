package com.lesson3.controller;

import com.lesson3.model.File;
import com.lesson3.model.Storage;
import com.lesson3.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StorageController {
    @Autowired
    private StorageService storageService;

    @RequestMapping(method = RequestMethod.POST, value = "/saveStorage", produces = "application/json")
    public @ResponseBody
    Storage save(Storage storage) {
        return storageService.save(storage);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateStorage", produces = "application/json")
    public @ResponseBody
    Storage update(Storage storage) {
        return storageService.update(storage);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteStorage", produces = "application/json")
    public @ResponseBody Storage delete(long id) {
        return storageService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findStorage", produces = "application/json")
    public @ResponseBody
    Storage findById(long id) {
        return storageService.findById(id);
    }
}
