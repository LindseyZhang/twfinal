package com.tw.service.Promotion;

import com.tw.model.PayItem;

public final class PromotionDiscount extends PromotionBase {
    private static final double DISCOUNT = 0.95;

    public PromotionDiscount(String barcodes) {
        super(barcodes);
    }

    @Override
    public double getMoneySavedByCurrentItem(PayItem item) {
        return item.getCount()*item.getPrice()*(1-DISCOUNT);
    }

    @Override
    public String getItemSellDetail(PayItem item) {
        double savedMoney = getMoneySavedByCurrentItem(item);
        return item.toString() + "，小计：" + (item.getCount()*item.getPrice() - savedMoney) +",节省：" + savedMoney;
    }

    @Override
    public String getPromotionName() {
        return null;
    }

    @Override
    public String getItemPromotionDetail(PayItem item) {
        return null;
    }
}
