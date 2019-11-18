package com.lesson3.controller;

import com.lesson3.model.File;
import com.lesson3.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController implements IFileController {
    @Autowired
    private IFileService iFileService;

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/saveFile", produces = "text/plain")
    public @ResponseBody File save(File file) {
        iFileService.save(file);
        return file;
    }

    @Override
    public File update(File file) {
        return null;
    }

    @Override
    public File delete(long id) {
        return null;
    }

    @Override
    public File findById(long id) {
        return null;
    }
}
