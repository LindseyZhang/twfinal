package com.tw.model;

/**
 * Created by qq422 on 2016/7/19.
 */
public class PromotionData {
    private String promotionName;
    private String[] needPromotionBarcodes;

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String[] getNeedPromotionBarcodes() {
        return needPromotionBarcodes;
    }

    public void setNeedPromotionBarcodes(String[] needPromotionBarcodes) {
        this.needPromotionBarcodes = needPromotionBarcodes;
    }
}
