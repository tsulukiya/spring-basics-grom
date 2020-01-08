package com.lesson5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    private DAO dao;

    @Autowired
    public TestController(DAO dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/item/save", produces = "text/plain")
    public @ResponseBody
    String save(Item item) {
        dao.save(item);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/item/update", produces = "text/plain")
    public @ResponseBody
    String update(Item item) {
        dao.update(item);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/item/findById", produces = "text/plain")
    public @ResponseBody
    String findById(Long id) {
        dao.findById(id);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/item/delete", produces = "text/plain")
    public @ResponseBody
    String delete(Long id) {
        dao.delete(id);
        return "ok";
    }


}
