package com.lesson2.part2.ItemService;

import com.lesson2.part2.Entity.Item;
import com.lesson2.part2.ItemDAO.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ItemService {
    @Autowired
    private ItemDAO itemDAO;

    public Item save(Item item) {
        item.setDateCreated(new Date());
        item.setLastUpdatedDate(new Date());
        return itemDAO.save(item);
    }

    public Item update(Item item) {
       return itemDAO.update(item);
    }

    public Item delete(long id) {
       return itemDAO.delete(id);
    }

    public Item findById(long id) {
        return itemDAO.findById(id);
    }
}
