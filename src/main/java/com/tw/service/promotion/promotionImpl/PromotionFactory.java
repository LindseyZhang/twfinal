package com.tw.service.promotion.promotionImpl;

import com.tw.service.promotion.PromotionInterface;

/**
 * Created by qq422 on 2016/7/19.
 */
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
