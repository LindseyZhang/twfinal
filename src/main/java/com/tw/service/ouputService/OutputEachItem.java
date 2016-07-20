package com.tw.service.ouputService;

import com.tw.model.OutputItem;
import com.tw.model.PromotedItem;

import java.util.List;

/**
 * Created by qq422 on 2016/7/19.
 */
public interface OutputEachItem {
    /**
     * 根据不同的优惠方式，生成不同的输出，输出分为前半段和后半段
     * @param promotedItems
     * @return OutputItem
     */
    public OutputItem getEachItem(List<PromotedItem> promotedItems);
}
