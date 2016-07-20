package com.tw.service.ouput;

import com.tw.model.ComputedItem;

public interface OutputService {
    /**
     * 将处理完的PromotedItem，处理成String结果
     * @param computedItem
     * @return
     */
    public String getOutput(ComputedItem computedItem);
}
