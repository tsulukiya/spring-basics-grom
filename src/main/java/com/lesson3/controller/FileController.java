package com.lesson3.controller;

import com.lesson3.model.File;
import com.lesson3.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(method = RequestMethod.POST, value = "/saveFile", produces = "text/plain")
    public @ResponseBody
    File save(File file) {
        fileService.save(file);
        return file;
    }

    public File update(File file) {
        return null;
    }

    public File delete(long id) {
        return null;
    }

    public File findById(long id) {
        return null;
    }
}
