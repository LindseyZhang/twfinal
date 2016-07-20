package com.tw.service.ouputService.outputImpl;

import com.tw.service.ouputService.OutputEachItem;
import com.tw.service.promotion.promotionImpl.BuyTwoGiveOne;
import com.tw.service.promotion.promotionImpl.SellBy95;

/**
 * Created by qq422 on 2016/7/19.
 */
public class OutputMethodFactory {
    public static OutputEachItem getOutputMethod(String promotionName){
        switch (promotionName){
            case "BUY_TWO_GET_ONE_FREE":
                return new OutputBuyTwoGiveOne();
            case "SELL_BY_95":
                return new OutputSellBy95();
            default:
                throw new RuntimeException("No promotion's name is "+promotionName);
        }
    }
}
