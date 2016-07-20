package com.tw.service.ouputService.outputImpl;

import com.tw.model.PromotedItem;

/**
 * Created by qq422 on 2016/7/19.
 */
public class OutputServiceBase {
    protected String getHead(){
        return "***<没钱赚商店>购物清单***\n";
    }
    protected String getLine(){
        return "---------------------------------------\n";
    }
    protected String getbotton(){
        return "***************************************\n";
    }


}
