package com.tw.service.ouputService.outputImpl;

import com.tw.model.ComputedItem;
import com.tw.model.OutputItem;
import com.tw.model.PayItem;
import com.tw.service.ouputService.OutputEachItem;
import com.tw.service.ouputService.OutputService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by qq422 on 2016/7/19.
 */
@Service
public class OutputServiceImpl extends OutputServiceBase implements OutputService {
    static Logger logger = Logger.getLogger (OutputServiceImpl.class.getName());
    /**
     * 将处理完的PromotedItem，处理成String结果
     *
     * @param computedItem
     * @return
     */
    @Override
    public String getOutput(ComputedItem computedItem) {
        if(computedItem==null){
            logger.error("computed item is null!");
            throw new NullPointerException("computed item is null!");
        }

        logger.info("compute finish and output result");
        StringBuilder output = new StringBuilder();
        StringBuilder front = new StringBuilder();
        StringBuilder last = new StringBuilder();
        output.append(getHead());
        if(!computedItem.getNormalPayitem().isEmpty()){
            for (PayItem item : computedItem.getNormalPayitem()) {
                front.append(item.toString());
            }
        }
        if(!computedItem.getPromotianName().isEmpty()) {
            for (String promotionName : computedItem.getPromotianName()) {
                OutputEachItem outputEachItem = OutputMethodFactory.getOutputMethod(promotionName);
                OutputItem outputItem = outputEachItem.getEachItem(computedItem.getPromotions().get(promotionName));
                front.append(outputItem.getFrontPart());
                last.append(outputItem.getLastPart());
            }
        }
        output.append(front);
        output.append(getLine());
        if(last.length()!=0) {
            output.append(last);
            output.append(getLine());
        }
        output.append("总计："+String.format("%.2f", computedItem.getTotalPrice())+"(元)\n");
        if(computedItem.getDiscountPrice()!=0) {
            output.append("节省：" + String.format("%.2f", computedItem.getDiscountPrice()) + "(元)\n");
        }
        output.append(getbotton());
        logger.info(output.toString());
        return output.toString();
    }


}
