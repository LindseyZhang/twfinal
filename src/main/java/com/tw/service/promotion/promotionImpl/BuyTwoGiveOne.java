package com.tw.service.promotion.promotionImpl;

import com.tw.model.PayItem;
import com.tw.model.PromotedItem;
import com.tw.service.promotion.PromotionInterface;

public class BuyTwoGiveOne implements PromotionInterface {

    public final int FREE_NUM = 2;


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
        int giveNum = payItem.getCount()/FREE_NUM;
        promotedItem.setDiscountPrice(payItem.getPrice()*giveNum);
        promotedItem.setSubtotal(payItem.getPrice()*payItem.getCount());
        promotedItem.setCount(payItem.getCount() + giveNum);
        return promotedItem;

    }
}
