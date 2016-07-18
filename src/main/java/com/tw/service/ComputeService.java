package com.tw.service;

import com.tw.dao.PromotionRepository;
import com.tw.entity.Promotion;
import com.tw.service.Promotion.PromotionBase;
import com.tw.service.Promotion.PromotionBuyTwoGetOneFree;
import com.tw.service.Promotion.PromotionDiscount;
import com.tw.service.Promotion.PromotionType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ComputeService {
    private List<PromotionBase> allPromotions;

    @Autowired
    public PromotionRepository promotionRepository;

    public ComputeService() {
        this.allPromotions = new ArrayList<PromotionBase>();
    }

    public void loadAllPromotions() {
        List<Promotion> allPromotionsEntity  = promotionRepository.findAll();
        for (Promotion promotion : allPromotionsEntity) {
            loadPromotion(promotion);
        }
    }

    private void loadPromotion(Promotion promotion) {
        switch(PromotionType.getPromotionType(promotion.getPromotionName())){
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
}
