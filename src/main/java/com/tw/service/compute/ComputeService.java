package com.tw.service.compute;

import com.tw.model.ComputedItem;
import com.tw.model.PayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qq422 on 2016/7/19.
 */
public interface ComputeService {
    /**
     * 将接到的买入商品列表处理成输出的ComputedItem类
     * @param payItems
     * @return ComputedItem
     */
    public ComputedItem computePromotion(List<PayItem> payItems);
}
