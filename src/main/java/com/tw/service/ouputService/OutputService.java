package com.tw.service.ouputService;

import com.tw.model.ComputedItem;
import com.tw.model.PromotedItem;

/**
 * Created by qq422 on 2016/7/19.
 */
public interface OutputService {
    /**
     * 将处理完的PromotedItem，处理成String结果
     * @param computedItem
     * @return
     */
    public String getOutput(ComputedItem computedItem);
}
