package com.tw.service.ouput.outputImpl;

import com.tw.model.ComputedItem;
import com.tw.model.OutputItem;
import com.tw.model.PayItem;
import com.tw.service.ouput.OutputEachItem;
import com.tw.service.ouput.OutputService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


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

        TransferComputedItemToOutput(computedItem, output, front, last);

        logger.info("final output：\n"+output.toString());
        return output.toString();
    }

    private void TransferComputedItemToOutput(ComputedItem computedItem, StringBuilder output, StringBuilder front, StringBuilder last) {
        output.append(getHead());
        getMiddelPart(computedItem, output, front, last);
        getLastPart(computedItem, output);
    }

    private void getLastPart(ComputedItem computedItem, StringBuilder output) {
        output.append("总计："+String.format("%.2f", computedItem.getTotalPrice())+"(元)\n");
        if(computedItem.getDiscountPrice()!=0) {
            output.append("节省：" + String.format("%.2f", computedItem.getDiscountPrice()) + "(元)\n");
        }
        output.append(getbotton());
    }

    private void getMiddelPart(ComputedItem computedItem, StringBuilder output, StringBuilder front, StringBuilder last) {
        getEachNormalItem(computedItem, front);
        getEachPromotionItem(computedItem, front, last);
        output.append(front);
        output.append(getLine());
        if(last.length()!=0) {
            output.append(last);
            output.append(getLine());
        }
    }

    private void getEachPromotionItem(ComputedItem computedItem, StringBuilder front, StringBuilder last) {
        if(!computedItem.getPromotianName().isEmpty()) {
            for (String promotionName : computedItem.getPromotianName()) {
                OutputEachItem outputEachItem = OutputMethodFactory.getOutputMethod(promotionName);
                OutputItem outputItem = outputEachItem.getEachItem(computedItem.getPromotions().get(promotionName));
                front.append(outputItem.getFrontPart());
                last.append(outputItem.getLastPart());
            }
        }
    }

    private void getEachNormalItem(ComputedItem computedItem, StringBuilder front) {
        if(!computedItem.getNormalPayitem().isEmpty()){
            for (PayItem item : computedItem.getNormalPayitem()) {
                front.append(item.toString());
            }
        }
    }


}
