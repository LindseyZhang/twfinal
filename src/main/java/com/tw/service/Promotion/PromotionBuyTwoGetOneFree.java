package com.tw.service.Promotion;

import com.tw.model.PayItem;

public final class PromotionBuyTwoGetOneFree extends PromotionBase {
    private static final String PROMOTION_TITLE = "买二赠一商品";
    private static final int BASE_COUNT = 2;

    public PromotionBuyTwoGetOneFree(String barcodes) {
        super(barcodes);
    }

    @Override
    public double getMoneySavedByCurrentItem(PayItem item) {
        return getFreeItemCount(item)*item.getPrice();
    }

    @Override
    public String getItemSellDetail(PayItem item) {
        double sum = item.getCount()*item.getPrice() - getMoneySavedByCurrentItem(item);
        return item.toString() + "，小计：" + String.format("%.2f",sum) + "(元)\n";
    }

    @Override
    public String getPromotionName() {
        return PROMOTION_TITLE;
    }

    @Override
    public String getItemPromotionDetail(PayItem item) {
        return "名称：" + item.getName() + ",数量：" + getFreeItemCount(item) + item.getUnit() +"\n";
    }

    private int getFreeItemCount(PayItem item) {
        return (int) item.getCount() /(BASE_COUNT+1);
    }
}
