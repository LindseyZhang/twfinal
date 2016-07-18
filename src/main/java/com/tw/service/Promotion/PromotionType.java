package com.tw.service.Promotion;

public enum PromotionType {
    SELL_BY_95,
    BUY_TWO_GET_ONE_FREE;

    public static PromotionType getPromotionType(String input){
        return valueOf(input);
    }
}
