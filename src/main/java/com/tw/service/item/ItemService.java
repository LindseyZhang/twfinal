package com.tw.service.item;

import com.tw.entity.Item;
import com.tw.entity.Promotion;

import java.util.HashMap;
import java.util.List;

/**
 * Created by qq422 on 2016/7/20.
 */
public interface ItemService {

    public HashMap<String, Item> loadItemFromDBToMap();

    public List<Promotion> loadPromotionFromDB();

}
