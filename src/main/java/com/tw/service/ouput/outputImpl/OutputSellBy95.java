package com.tw.service.ouput.outputImpl;

import com.tw.model.OutputItem;
import com.tw.model.PromotedItem;
import com.tw.service.ouput.OutputEachItem;

import java.util.List;


public class OutputSellBy95 implements OutputEachItem {
    /**
     * 根据不同的优惠方式，生成不同的输出，输出分为前半段和后半段
     *
     * @param promotedItems
     * @return OutputItem
     */
    @Override
    public OutputItem getEachItem(List<PromotedItem> promotedItems) {
        StringBuilder front = new StringBuilder();
        StringBuilder last = new StringBuilder();
        transferToStringBuilder(promotedItems, front);
        OutputItem ouputItem = new OutputItem();
        ouputItem.setFrontPart(front);
        ouputItem.setLastPart(last);
        return ouputItem;
    }

    private void transferToStringBuilder(List<PromotedItem> promotedItems, StringBuilder front) {
        for (PromotedItem item : promotedItems) {
            front.append("名称："+item.getName()+"，");
            front.append("数量："+item.getCount()+item.getUnit()+"，");
            front.append("单价："+String.format("%.2f", item.getPrice())+"(元)，");
            front.append("小计："+String.format("%.2f", item.getSubtotal())+"(元)，");
            front.append("节省："+String.format("%.2f", item.getDiscountPrice())+"(元)\n");
        }
    }
}
