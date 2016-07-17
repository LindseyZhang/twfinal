package com.tw.service.compute;

import com.tw.model.PayItem;
import com.tw.model.PromotionItem;

import java.util.ArrayList;

/**
 * Created by qq422 on 2016/7/17.
 */
public interface Compute {
    public PromotionItem getPromotionItem(ArrayList<PayItem> PayItems);
}
