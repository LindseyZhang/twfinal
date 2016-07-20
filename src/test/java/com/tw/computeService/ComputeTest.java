package com.tw.computeService;

import com.tw.entity.Promotion;
import com.tw.model.ComputedItem;
import com.tw.model.PayItem;
import com.tw.model.PromotedItem;
import com.tw.service.item.itemServiceImpl.ItemServiceImpl;
import com.tw.service.compute.ComputeService;
import com.tw.service.compute.computeImpl.ComputeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ComputeTest.class)
public class ComputeTest {
    @Mock
    ItemServiceImpl itemServiceImpl;


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
        when(itemServiceImpl.loadPromotionFromDB()).thenReturn(promotions);
        ((ComputeServiceImpl)computeService).itemServiceImpl = itemServiceImpl;
        ComputedItem computedItem = computeService.computePromotion(payItems);
        System.out.println();
        ComputedItem execptComputedItem = new ComputedItem();
        execptComputedItem.setDiscountPrice(5.00);
        execptComputedItem.setTotalPrice(25.00);
        PayItem payItem = new PayItem("苹果","ITEM000005","斤",5.50,2);
        execptComputedItem.setNormalPayitem(new ArrayList<PayItem>());
        execptComputedItem.getNormalPayitem().add(payItem);
        ArrayList<String> promotionNames = new ArrayList<>();
        promotionNames.add("BUY_TWO_GET_ONE_FREE");
        promotionNames.add("SELL_BY_95");
        execptComputedItem.setPromotianName(promotionNames);

        PromotedItem promotedItem1 = new PromotedItem();
        promotedItem1.setName("可口可乐");
        promotedItem1.setCount(4);
        promotedItem1.setSubtotal(9.00);
        promotedItem1.setUnit("瓶");
        promotedItem1.setBarcode("ITEM000001");
        promotedItem1.setDiscountPrice(3.00);
        promotedItem1.setPrice(3.00);

        PromotedItem promotedItem2 = new PromotedItem();
        promotedItem2.setName("羽毛球");
        promotedItem2.setCount(7);
        promotedItem2.setSubtotal(5.00);
        promotedItem2.setUnit("个");
        promotedItem2.setBarcode("ITEM000003");
        promotedItem2.setDiscountPrice(2.0);
        promotedItem2.setPrice(1.00);
        HashMap<String,ArrayList<PromotedItem>> map = new HashMap<>();
        ArrayList<PromotedItem> list1 = new ArrayList<>();
        list1.add(promotedItem1);
        ArrayList<PromotedItem> list2 = new ArrayList<>();
        list1.add(promotedItem2);
        map.put("BUY_TWO_GET_ONE_FREE",list1);
        map.put("SELL_BY_95",list2);
        execptComputedItem.setPromotions(map);

        assertEquals(execptComputedItem,computedItem);



    }

    @Test
    public void testComputServiceWithBuyTwoGiveOne() {
        List<PayItem> payItems = new ArrayList<PayItem>();
        payItems.add(new PayItem("可口可乐","ITEM000001","瓶", 3.00, 3));
        payItems.add(new PayItem("羽毛球","ITEM000003","个", 1.00, 5));
        payItems.add(new PayItem("苹果","ITEM000005","斤", 5.50, 2));
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("BUY_TWO_GET_ONE_FREE","ITEM000001,ITEM000003"));
        ComputeService computeService = new ComputeServiceImpl();
        when(itemServiceImpl.loadPromotionFromDB()).thenReturn(promotions);
        ((ComputeServiceImpl)computeService).itemServiceImpl = itemServiceImpl;
        ComputedItem computedItem = computeService.computePromotion(payItems);
        System.out.println();
        ComputedItem execptComputedItem = new ComputedItem();
        execptComputedItem.setDiscountPrice(5.00);
        execptComputedItem.setTotalPrice(25.00);
        PayItem payItem = new PayItem("苹果","ITEM000005","斤",5.50,2);
        execptComputedItem.setNormalPayitem(new ArrayList<PayItem>());
        execptComputedItem.getNormalPayitem().add(payItem);
        ArrayList<String> promotionNames = new ArrayList<>();
        promotionNames.add("BUY_TWO_GET_ONE_FREE");
        execptComputedItem.setPromotianName(promotionNames);

        PromotedItem promotedItem1 = new PromotedItem();
        promotedItem1.setName("可口可乐");
        promotedItem1.setCount(4);
        promotedItem1.setSubtotal(9.00);
        promotedItem1.setUnit("瓶");
        promotedItem1.setBarcode("ITEM000001");
        promotedItem1.setDiscountPrice(3.00);
        promotedItem1.setPrice(3.00);

        PromotedItem promotedItem2 = new PromotedItem();
        promotedItem2.setName("羽毛球");
        promotedItem2.setCount(7);
        promotedItem2.setSubtotal(5.00);
        promotedItem2.setUnit("个");
        promotedItem2.setBarcode("ITEM000003");
        promotedItem2.setDiscountPrice(2.0);
        promotedItem2.setPrice(1.00);
        HashMap<String,ArrayList<PromotedItem>> map = new HashMap<>();
        ArrayList<PromotedItem> list1 = new ArrayList<>();
        list1.add(promotedItem1);
        ArrayList<PromotedItem> list2 = new ArrayList<>();
        list1.add(promotedItem2);
        map.put("BUY_TWO_GET_ONE_FREE",list1);
        execptComputedItem.setPromotions(map);

        assertEquals(execptComputedItem,computedItem);

    }
    @Test
    public void testComputServiceSellBy95() {
        List<PayItem> payItems = new ArrayList<PayItem>();
        payItems.add(new PayItem("可口可乐","ITEM000001","瓶", 3.00, 3));
        payItems.add(new PayItem("羽毛球","ITEM000003","个", 1.00, 5));
        payItems.add(new PayItem("苹果","ITEM000005","斤", 5.50, 2));
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("SELL_BY_95","ITEM000003"));
        ComputeService computeService = new ComputeServiceImpl();
        when(itemServiceImpl.loadPromotionFromDB()).thenReturn(promotions);
        ((ComputeServiceImpl)computeService).itemServiceImpl = itemServiceImpl;
        ComputedItem computedItem = computeService.computePromotion(payItems);
        System.out.println();
        ComputedItem execptComputedItem = new ComputedItem();
        execptComputedItem.setDiscountPrice(0.25);
        execptComputedItem.setTotalPrice(24.75);
        PayItem payItem = new PayItem("苹果","ITEM000005","斤",5.50,2);
        PayItem payItem1 = new PayItem("可口可乐","ITEM000001","瓶",3.00,3);
        execptComputedItem.setNormalPayitem(new ArrayList<PayItem>());
        execptComputedItem.getNormalPayitem().add(payItem1);
        execptComputedItem.getNormalPayitem().add(payItem);
        ArrayList<String> promotionNames = new ArrayList<>();
        promotionNames.add("SELL_BY_95");
        execptComputedItem.setPromotianName(promotionNames);


        PromotedItem promotedItem2 = new PromotedItem();
        promotedItem2.setName("羽毛球");
        promotedItem2.setCount(5);
        promotedItem2.setSubtotal(4.75);
        promotedItem2.setUnit("个");
        promotedItem2.setBarcode("ITEM000003");
        promotedItem2.setDiscountPrice(0.25);
        promotedItem2.setPrice(1.00);
        HashMap<String,ArrayList<PromotedItem>> map = new HashMap<>();
        ArrayList<PromotedItem> list1 = new ArrayList<>();
        list1.add(promotedItem2);
        map.put("SELL_BY_95",list1);
        execptComputedItem.setPromotions(map);

        assertEquals(execptComputedItem,computedItem);



    }
}
