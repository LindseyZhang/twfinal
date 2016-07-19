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
        return item.toString() + "，小计：" + String.format("%.2f",item.getCount()*item.getPrice() - savedMoney)
                 + "(元),节省：" + String.format("%.2f",savedMoney) + "(元)\n";
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
