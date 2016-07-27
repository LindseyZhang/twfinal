package com.tw.service;

import com.tw.dao.PromotionRepository;
import com.tw.entity.Promotion;
import com.tw.model.PayItem;
import com.tw.service.Promotion.PromotionBase;
import com.tw.service.Promotion.PromotionBuyTwoGetOneFree;
import com.tw.service.Promotion.PromotionDiscount;
import com.tw.service.Promotion.PromotionType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosService {
    private List<PromotionBase> allPromotions;
    private String storeName = "没钱赚商店";

    public static final double PRECISION = 0.00001;

    @Autowired
    public PromotionRepository promotionRepository;

    public PosService() {
        this.allPromotions = new ArrayList<PromotionBase>();
    }

    public void loadAllPromotions() {
        List<Promotion> allPromotionsEntity = promotionRepository.findAll();
        for (Promotion promotion : allPromotionsEntity) {
            loadPromotion(promotion);
        }
    }

    private void loadPromotion(Promotion promotion) {
        switch (PromotionType.getPromotionType(promotion.getPromotionName())) {
            case SELL_BY_95:
                allPromotions.add(new PromotionDiscount(promotion.getBarcodes()));
                break;
            case BUY_TWO_GET_ONE_FREE:
                allPromotions.add(new PromotionBuyTwoGetOneFree(promotion.getBarcodes()));
                break;
        }
    }

    public List<PromotionBase> getAllPromotions() {
        return allPromotions;
    }

    public String getOutput(List<PayItem> payItems) {
        Map<PromotionBase,StringBuilder> promotionAndPromotionDetailPair =
                new HashMap<PromotionBase,StringBuilder>();
        initPromotionAndPromotionDetailPair(promotionAndPromotionDetailPair);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***<" + storeName + ">购物清单***\n");

        double sum = 0.00;
        double saveMoney = 0.00;
        double savePerItem = 0.00;
        for (PayItem payItem : payItems) {
            PromotionBase promotion = getItemPromotionMethod(payItem);
            StringBuilder promotionBuilder = promotionAndPromotionDetailPair.get(promotion);
            sum += payItem.getPrice() * payItem.getCount();
            if (promotion.isValidPromotionItem(payItem)) {
                savePerItem = promotion.getMoneySavedByCurrentItem(payItem);
                if (Math.abs(savePerItem) > PRECISION) {
                    saveMoney += savePerItem;
                    if (null != promotion.getItemPromotionDetail(payItem)) {
                        promotionBuilder.append(promotion.getItemPromotionDetail(payItem));
                    }
                }
            }
            stringBuilder.append(promotion.getItemSellDetail(payItem));
        }

        stringBuilder.append(getPromotionBlockInReceipt(promotionAndPromotionDetailPair));

        stringBuilder.append("-----------------------------\n");
        stringBuilder.append("总计：" + String.format("%.2f", sum - saveMoney) + "(元)\n");
        if (saveMoney > PRECISION) {
            stringBuilder.append("节省：" + String.format("%.2f", saveMoney) + "(元)\n");
        }
        stringBuilder.append("******************************");
        return stringBuilder.toString();
    }

    private PromotionBase getItemPromotionMethod(PayItem payItem) {
        List<PromotionBase> validPromotions = new ArrayList<PromotionBase>();
        getValidPromotions(payItem, validPromotions);
        return decideAPromotionFromValidPromotions(validPromotions);
    }

    private PromotionBase decideAPromotionFromValidPromotions(List<PromotionBase> validPromotions) {
        if (validPromotions.size() == 1){
            return validPromotions.get(0);
        }
        if (validPromotions.size() == 2){
            return validPromotions.get(0).getClass().equals(PromotionDiscount.class)?
                    validPromotions.get(1): validPromotions.get(0);
        }
        return new PromotionBase();
    }

    private void getValidPromotions(PayItem payItem, List<PromotionBase> validPromotion) {
        for(PromotionBase promotion:allPromotions){
            if(promotion.isValidPromotionItem(payItem)){
                validPromotion.add(promotion);
            }
        }
    }

    private void initPromotionAndPromotionDetailPair(Map<PromotionBase,StringBuilder> promotionStrBuilderPair) {
        for(PromotionBase promotion:allPromotions){
            promotionStrBuilderPair.put(promotion,new StringBuilder());
        }
    }

    private String getPromotionBlockInReceipt(Map<PromotionBase, StringBuilder> promotionAndPromotionDetailPair) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PromotionBase promotionType: promotionAndPromotionDetailPair.keySet()){
            String promotionDetail = promotionAndPromotionDetailPair.get(promotionType).toString();
            if (!promotionDetail.isEmpty()) {
                stringBuilder.append("-----------------------------\n");
                stringBuilder.append(promotionType.getPromotionName()).append(":\n");
                stringBuilder.append(promotionDetail);
            }
        }
        return stringBuilder.toString();
    }
}
