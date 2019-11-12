package com.lesson2.part1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private Route route;
    @Autowired
    private Step step;
    @Autowired
    private Service service;

    @RequestMapping(method = RequestMethod.GET, value = "/callBean", produces = "text/plain")
    public @ResponseBody
    String CallByBean() {
        return
        route.getId() +
        route.getSteps() +
        step.getId() +
        step.getParamsServiceFrom() +
        step.getParamsServiceTo() +
        step.getServiceFrom() +
        step.getServiceTo() +
        service.getId() +
        service.getName() +
        service.getParamsToCall();
    }
}
