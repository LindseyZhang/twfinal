package com.tw.service.promotion;

import com.tw.model.PayItem;
import com.tw.model.PromotedItem;

public interface PromotionInterface {
    /**
     * 根据不同的优惠方式处理购买的商品，并返回处理后的优惠项
     * @param payItem
     * @return PromotedItem
     */
    public PromotedItem computePromotion(PayItem payItem);
}
