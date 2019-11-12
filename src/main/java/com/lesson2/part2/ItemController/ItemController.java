package com.lesson2.part2.ItemController;

import com.lesson2.part2.Entity.Item;
import com.lesson2.part2.ItemService.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;


    @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "text/plain")
    public @ResponseBody String findById(long id) {
        return itemService.findById(id).toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", produces = "text/plain")
    public @ResponseBody String save(Item item) {
        itemService.save(item);
        return item.toString();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "text/plain")
    public @ResponseBody String update(Item item) {
        itemService.update(item);
        return item.toString();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = "text/plain")
    public @ResponseBody String delete(Item item) {
        itemService.delete(item.getId());
        return item.toString();
    }
}
