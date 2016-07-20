package com.tw.service.promotion.promotionImpl;

import com.tw.model.PayItem;
import com.tw.model.PromotedItem;
import com.tw.service.promotion.PromotionInterface;

import java.math.BigDecimal;

public class SellBy95 implements PromotionInterface {
    public final double DISCOUNT_RATE = 0.95;
    public final double NO_DISCOUNT_RATE = 0.05;
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
        double result = (payItem.getPrice()*payItem.getCount())*DISCOUNT_RATE;
        BigDecimal bg = new BigDecimal(result);
        double temp = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        promotedItem.setSubtotal(temp);
        promotedItem.setDiscountPrice((payItem.getPrice()*payItem.getCount())-temp);
        promotedItem.setCount(payItem.getCount());
        return promotedItem;
    }
}
