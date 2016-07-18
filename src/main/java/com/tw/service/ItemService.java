package com.tw.service;

import com.tw.dao.ItemRepository;
import com.tw.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    public ItemRepository itemRepository;

    public HashMap<String, Item> loadItemToMap() {
        HashMap<String, Item> itemMap = new HashMap<>();
        List<Item> itemList = itemRepository.findAll();

        for (Item item : itemList) {
            itemMap.put(item.getBarcode(), item);
        }
        return itemMap;
    }
}
