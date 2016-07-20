package com.tw.service.ouputService.outputImpl;

import com.tw.service.ouputService.OutputEachItem;


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
