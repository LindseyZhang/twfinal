package com.tw.service.compute.computeImpl;

import com.tw.entity.Promotion;
import com.tw.model.ComputedItem;
import com.tw.model.PayItem;
import com.tw.model.PromotedItem;
import com.tw.model.PromotionData;
import com.tw.service.item.itemServiceImpl.ItemServiceImpl;
import com.tw.service.compute.ComputeService;
import com.tw.service.promotion.PromotionInterface;
import com.tw.service.promotion.promotionImpl.PromotionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ComputeServiceImpl implements ComputeService {
    static Logger logger = Logger.getLogger (ComputeServiceImpl.class.getName());

    @Autowired
    public ItemServiceImpl itemServiceImpl;

    List<PromotionData> promotionDatas;

    ComputedItem computedItem;


    /**
     * 将接到的买入商品列表处理成输出的ComputedItem类
     * @param payItems
     * @return ComputedItem
     */
    @Override
    public ComputedItem computePromotion(List<PayItem> payItems) {

        computedItem = new ComputedItem();
        if(payItems.isEmpty()) {
            logger.error("input List is no payitems!");
            throw new NullPointerException("input List is no payitems!");
        }
        logger.info("start to compute input List");

        initPromotionData();

        for (PayItem payItem : payItems) {
            computeEachItem(payItem);
        }
        return computedItem;
    }

    private void computeEachItem(PayItem payItem) {
        boolean isPromotion = false;
        isPromotion = computePromotionItem(payItem, isPromotion);
        computeNormalItem(payItem, isPromotion);
    }

    private void computeNormalItem(PayItem payItem, boolean isPromotion) {
        if(!isPromotion){
            computedItem.getNormalPayitem().add(payItem);
            computedItem.setTotalPrice(computedItem.getTotalPrice() + payItem.getCount()*payItem.getPrice());
        }
    }

    private boolean computePromotionItem(PayItem payItem, boolean isPromotion) {
        for (PromotionData promotionData : promotionDatas ) {
            isPromotion = computeItemAccordingType(payItem, isPromotion, promotionData);
            if(isPromotion)
                break;
        }
        return isPromotion;
    }

    private boolean computeItemAccordingType(PayItem payItem, boolean isPromotion, PromotionData promotionData) {
        for (String enableBarcode : promotionData.getNeedPromotionBarcodes()) {
            if(payItem.getBarcode().equals(enableBarcode)){
                PromotionInterface promotionMethod = PromotionFactory.getPromotion(promotionData.getPromotionName());
                PromotedItem promotionItem = promotionMethod.computePromotion(payItem);
                if(promotionItem.getDiscountPrice()!=0) {
                    computedItem.getPromotions().get(promotionData.getPromotionName()).add(promotionItem);
                    computedItem.setTotalPrice(computedItem.getTotalPrice() + promotionItem.getSubtotal());
                    computedItem.setDiscountPrice(computedItem.getDiscountPrice() + promotionItem.getDiscountPrice());
                    isPromotion = true;
                    break;
                }
            }
        }
        return isPromotion;
    }

    private void initPromotionData() {
        List<Promotion> promotions = itemServiceImpl.loadPromotionFromDB();
        promotionDatas = new ArrayList<>();
        for (Promotion item:promotions) {
            transferDBdataToPromotionList(item);
        }
        logger.info("init Promotion Data success!");
    }

    private void transferDBdataToPromotionList(Promotion item) {
        PromotionData promotionData = new PromotionData();
        promotionData.setPromotionName(item.getPromotionName());
        String[] barcodes = item.getBarcodes().split(",");
        promotionData.setNeedPromotionBarcodes(barcodes);
        promotionDatas.add(promotionData);
        computedItem.getPromotions().put(item.getPromotionName(), new ArrayList<PromotedItem>());
        computedItem.getPromotianName().add(item.getPromotionName());
    }
}
