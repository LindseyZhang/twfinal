package com.tw.outputService;

import com.tw.model.ComputedItem;
import com.tw.model.PayItem;
import com.tw.model.PromotedItem;
import com.tw.service.ouput.OutputService;
import com.tw.service.ouput.outputImpl.OutputServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OutputTest.class)
public class OutputTest {


    @Test
    public void testOutputServicewithAllSituation(){
        ComputedItem computedItem = new ComputedItem();
        computedItem.setPromotianName(new ArrayList<String>());
        computedItem.getPromotianName().add("BUY_TWO_GET_ONE_FREE");
        computedItem.getPromotianName().add("SELL_BY_95");
        PayItem payItem = new PayItem("可口可乐","ITEM000001","瓶",3.00,3);
        computedItem.getNormalPayitem().add(payItem);
        HashMap<String,ArrayList<PromotedItem>> map = new HashMap<>();
        PromotedItem promotedItem1 = new PromotedItem();
        promotedItem1.setName("羽毛球");
        promotedItem1.setCount(6);
        promotedItem1.setSubtotal(4.00);
        promotedItem1.setUnit("个");
        promotedItem1.setBarcode("ITEM000003");
        promotedItem1.setDiscountPrice(2.00);
        promotedItem1.setPrice(1.00);
        PromotedItem promotedItem2 = new PromotedItem();
        promotedItem2.setName("苹果");
        promotedItem2.setCount(2);
        promotedItem2.setSubtotal(10.45);
        promotedItem2.setUnit("斤");
        promotedItem2.setBarcode("ITEM000005");
        promotedItem2.setDiscountPrice(0.55);
        promotedItem2.setPrice(5.50);
        ArrayList<PromotedItem> list1 = new ArrayList<>();
        list1.add(promotedItem1);
        ArrayList<PromotedItem> list2 = new ArrayList<>();
        list2.add(promotedItem2);
        map.put("BUY_TWO_GET_ONE_FREE",list1);
        map.put("SELL_BY_95",list2);
        computedItem.setDiscountPrice(2.55);
        computedItem.setTotalPrice(23.5);
        computedItem.setPromotions(map);
        OutputService outputService = new OutputServiceImpl();
        String result = outputService.getOutput(computedItem);
        System.out.println(result);
        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
                + "名称：可口可乐，数量：3瓶，单价：3.00\\(元\\)，小计：9.00\\(元\\)\n"
                + "名称：羽毛球，数量：6个，单价：1.00\\(元\\)，小计：4.00\\(元\\)\n"
                + "名称：苹果，数量：2斤，单价：5.50\\(元\\)，小计：10.45\\(元\\)，节省：0.55\\(元\\)\n"
                + "-{20,50}\n"
                + "买二赠一商品：\n"
                + "名称：羽毛球，数量：2个\n"
                + "-{20,50}\n"
                + "总计：23.50\\(元\\)\n"
                + "节省：2.55\\(元\\)\n"
                + "\\*{20,50}\n";
        assertTrue(result.matches(expectResult));
    }

    @Test
    public void testOutputServicewithBuyTwoGiveOneSituation(){
        ComputedItem computedItem = new ComputedItem();
        computedItem.setPromotianName(new ArrayList<String>());
        computedItem.getPromotianName().add("BUY_TWO_GET_ONE_FREE");
        PayItem payItem = new PayItem("苹果","ITEM000005","斤",5.50,2);
        computedItem.getNormalPayitem().add(payItem);
        HashMap<String,ArrayList<PromotedItem>> map = new HashMap<>();
        PromotedItem promotedItem1 = new PromotedItem();
        promotedItem1.setName("可口可乐");
        promotedItem1.setCount(3);
        promotedItem1.setSubtotal(6.00);
        promotedItem1.setUnit("瓶");
        promotedItem1.setBarcode("ITEM000001");
        promotedItem1.setDiscountPrice(3.00);
        promotedItem1.setPrice(3.00);
        PromotedItem promotedItem2 = new PromotedItem();
        promotedItem2.setName("羽毛球");
        promotedItem2.setCount(5);
        promotedItem2.setSubtotal(4.00);
        promotedItem2.setUnit("个");
        promotedItem2.setBarcode("ITEM000003");
        promotedItem2.setDiscountPrice(1);
        promotedItem2.setPrice(1);
        ArrayList<PromotedItem> list1 = new ArrayList<>();
        list1.add(promotedItem1);
        list1.add(promotedItem2);
        map.put("BUY_TWO_GET_ONE_FREE",list1);
        computedItem.setDiscountPrice(4);
        computedItem.setTotalPrice(21);
        computedItem.setPromotions(map);
        OutputService outputService = new OutputServiceImpl();
        String result = outputService.getOutput(computedItem);
        System.out.println(result);
        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
                + "名称：苹果，数量：2斤，单价：5.50\\(元\\)，小计：11.00\\(元\\)\n"
                + "名称：可口可乐，数量：3瓶，单价：3.00\\(元\\)，小计：6.00\\(元\\)\n"
                + "名称：羽毛球，数量：5个，单价：1.00\\(元\\)，小计：4.00\\(元\\)\n"
                + "-{20,50}\n"
                + "买二赠一商品：\n"
                + "名称：可口可乐，数量：1瓶\n"
                + "名称：羽毛球，数量：1个\n"
                + "-{20,50}\n"
                + "总计：21.00\\(元\\)\n"
                + "节省：4.00\\(元\\)\n"
                + "\\*{20,50}\n";
        assertTrue(result.matches(expectResult));
    }

    @Test
    public void testOutputServicewithSellBy95Situation(){
        ComputedItem computedItem = new ComputedItem();
        computedItem.setPromotianName(new ArrayList<String>());
        computedItem.getPromotianName().add("SELL_BY_95");
        PayItem payItem = new PayItem("可口可乐","ITEM000001","瓶",3.00,3);
        PayItem payItem2 = new PayItem("羽毛球","ITEM000003","个",1.00,5);
        computedItem.getNormalPayitem().add(payItem);
        computedItem.getNormalPayitem().add(payItem2);
        HashMap<String,ArrayList<PromotedItem>> map = new HashMap<>();
        PromotedItem promotedItem2 = new PromotedItem();
        promotedItem2.setName("苹果");
        promotedItem2.setCount(2);
        promotedItem2.setSubtotal(10.45);
        promotedItem2.setUnit("斤");
        promotedItem2.setBarcode("ITEM000005");
        promotedItem2.setDiscountPrice(0.55);
        promotedItem2.setPrice(5.5);
        ArrayList<PromotedItem> list1 = new ArrayList<>();
        list1.add(promotedItem2);
        map.put("SELL_BY_95",list1);
        computedItem.setDiscountPrice(0.55);
        computedItem.setTotalPrice(24.45);
        computedItem.setPromotions(map);
        OutputService outputService = new OutputServiceImpl();
        String result = outputService.getOutput(computedItem);
        System.out.println(result);
        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
                + "名称：可口可乐，数量：3瓶，单价：3.00\\(元\\)，小计：9.00\\(元\\)\n"
                + "名称：羽毛球，数量：5个，单价：1.00\\(元\\)，小计：5.00\\(元\\)\n"
                + "名称：苹果，数量：2斤，单价：5.50\\(元\\)，小计：10.45\\(元\\)，节省：0.55\\(元\\)\n"
                + "-{20,50}\n"
                + "总计：24.45\\(元\\)\n"
                + "节省：0.55\\(元\\)\n"
                + "\\*{20,50}\n";
        assertTrue(result.matches(expectResult));
    }

    @Test
    public void testOutputServiceNoPromotionSituation(){
        ComputedItem computedItem = new ComputedItem();
        computedItem.setPromotianName(new ArrayList<String>());
        computedItem.getPromotianName().add("BUY_TWO_GET_ONE_FREE");
        computedItem.getPromotianName().add("SELL_BY_95");
        PayItem payItem = new PayItem("可口可乐","ITEM000001","瓶",3.00,3);
        PayItem payItem2 = new PayItem("羽毛球","ITEM000003","个",1.00,5);
        PayItem payItem3 = new PayItem("苹果","ITEM000005","斤",5.50,2);
        computedItem.getNormalPayitem().add(payItem);
        computedItem.getNormalPayitem().add(payItem2);
        computedItem.getNormalPayitem().add(payItem3);
        HashMap<String,ArrayList<PromotedItem>> map = new HashMap<>();
        ArrayList<PromotedItem> list1 = new ArrayList<>();
        ArrayList<PromotedItem> list2 = new ArrayList<>();
        map.put("BUY_TWO_GET_ONE_FREE",list1);
        map.put("SELL_BY_95",list2);
        computedItem.setDiscountPrice(0);
        computedItem.setTotalPrice(25);
        computedItem.setPromotions(map);
        OutputService outputService = new OutputServiceImpl();
        String result = outputService.getOutput(computedItem);
        System.out.println(result);
        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
                + "名称：可口可乐，数量：3瓶，单价：3.00\\(元\\)，小计：9.00\\(元\\)\n"
                + "名称：羽毛球，数量：5个，单价：1.00\\(元\\)，小计：5.00\\(元\\)\n"
                + "名称：苹果，数量：2斤，单价：5.50\\(元\\)，小计：11.00\\(元\\)\n"
                + "-{20,50}\n"
                + "总计：25.00\\(元\\)\n"
                + "\\*{20,50}\n";
        assertTrue(result.matches(expectResult));
    }
}
