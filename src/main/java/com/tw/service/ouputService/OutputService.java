package com.tw.service.ouputService;

import com.tw.model.PromotedItem;

/**
 * Created by qq422 on 2016/7/19.
 */
public interface OutputService {
    /**
     * 将处理完的PromotedItem，处理成String结果
     * @param promotedItem
     * @return
     */
    public String getOutput(PromotedItem promotedItem);
}
