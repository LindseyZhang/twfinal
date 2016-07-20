package com.tw.service.item.itemServiceImpl;

import com.tw.dao.ItemRepository;
import com.tw.dao.PromotionRepository;
import com.tw.entity.Item;
import com.tw.entity.Promotion;
import com.tw.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public PromotionRepository promotionRepository;

    /**
     * 从数据库读取所有打折信息
     * @return HashMap<String, Item>
     */
    @Override
    public HashMap<String, Item> loadItemFromDBToMap() {
        HashMap<String, Item> itemMap = new HashMap<>();
        List<Item> itemList = itemRepository.findAll();

        for (Item item : itemList) {
            itemMap.put(item.getBarcode(), item);
        }

        return itemMap;
    }

    /**
     * 从数据库读取所有打折方式
     * @return ArrayList<PayItem>
     */
    @Override
    public List<Promotion> loadPromotionFromDB() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions;
    }
}
