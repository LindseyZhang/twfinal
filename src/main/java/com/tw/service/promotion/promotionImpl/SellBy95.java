package com.tw.service.promotion.promotionImpl;

import com.tw.model.PayItem;
import com.tw.model.PromotedItem;
import com.tw.service.promotion.PromotionInterface;

/**
 * Created by qq422 on 2016/7/19.
 */
public class SellBy95 implements PromotionInterface {
    public final double DISCOUNT_RATE = 0.95;
    /**
     * 根据不同的优惠方式处理购买的商品，并返回处理后的优惠项
     *
     * @param payItem
     * @return PromotedItem
     */
    @Override
    public PromotedItem computePromotion(PayItem payItem) {
        PromotedItem promotedItem = new PromotedItem();
        promotedItem.setBarcode(payItem.getBarcode());
        promotedItem.setName(payItem.getName());
        promotedItem.setPrice(payItem.getPrice());
        promotedItem.setUnit(payItem.getUnit());
        promotedItem.setDiscountPrice((payItem.getPrice()*payItem.getCount())*DISCOUNT_RATE);
        promotedItem.setSubtotal(payItem.getPrice()*payItem.getCount()-promotedItem.getDiscountPrice());
        promotedItem.setCount(payItem.getCount());
        return promotedItem;
    }
}
