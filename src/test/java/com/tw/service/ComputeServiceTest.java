package com.tw.service;

import com.tw.dao.PromotionRepository;
import com.tw.entity.Promotion;
import com.tw.service.Promotion.PromotionBase;
import com.tw.service.Promotion.PromotionBuyTwoGetOneFree;
import com.tw.service.Promotion.PromotionDiscount;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComputeServiceTest {
    private CompareFunction<PromotionBase> comparePromotionBase = new CompareFunction<PromotionBase>();

    @Mock
    private PromotionRepository promotionRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void loadAllPromotionTest(){
        List<PromotionBase> expectResult = new ArrayList<PromotionBase>();
        expectResult.add(new PromotionDiscount("ITEM000000"));
        expectResult.add(new PromotionBuyTwoGetOneFree("ITEM000001,ITEM000004"));

        List<Promotion> promotionsFromDB = new ArrayList<Promotion>();
        promotionsFromDB.add(new Promotion("SELL_BY_95","ITEM000000"));
        promotionsFromDB.add(new Promotion("BUY_TWO_GET_ONE_FREE","ITEM000001,ITEM000004"));
        ComputeService comSer = new ComputeService();
        comSer.promotionRepository = promotionRepository;
        when(promotionRepository.findAll()).thenReturn(promotionsFromDB);
        comSer.loadAllPromotions();

        assertTrue(new CompareFunction<PromotionBase>().TwoListIsEqual(comSer.getAllPromotions(),expectResult));
    }

}
