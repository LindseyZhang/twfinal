package com.tw.service.promotion.promotionImpl;

import com.tw.service.promotion.PromotionInterface;

public class PromotionFactory {
    public static PromotionInterface getPromotion(String promotionName){
        switch (promotionName){
            case "BUY_TWO_GET_ONE_FREE":
                return new BuyTwoGiveOne();
            case "SELL_BY_95":
                return new SellBy95();
            default:
                throw new RuntimeException("No promotion's name is "+promotionName);
        }
    }
}
