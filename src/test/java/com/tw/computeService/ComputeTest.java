package com.tw.computeService;

import com.tw.entity.Promotion;
import com.tw.model.ComputedItem;
import com.tw.model.PayItem;
import com.tw.service.ItemService;
import com.tw.service.compute.ComputeService;
import com.tw.service.compute.computeImpl.ComputeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by qq422 on 2016/7/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ComputeTest.class)
public class ComputeTest {
    @Mock
    ItemService itemService;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }
    @Test
    public void testComputService() {
        List<PayItem> payItems = new ArrayList<PayItem>();
        payItems.add(new PayItem("可口可乐","ITEM000001","瓶", 3.00, 3));
        payItems.add(new PayItem("羽毛球","ITEM000003","个", 1.00, 5));
        payItems.add(new PayItem("苹果","ITEM000005","斤", 5.50, 2));
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("BUY_TWO_GET_ONE_FREE","ITEM000001,ITEM000003"));
        promotions.add(new Promotion("SELL_BY_95","ITEM000003"));
        ComputeService computeService = new ComputeServiceImpl();
        when(itemService.loadPromotionFromDB()).thenReturn(promotions);
        ((ComputeServiceImpl)computeService).itemService = itemService;
        ComputedItem computedItem = computeService.computePromotion(payItems);
        System.out.println();

    }

}
