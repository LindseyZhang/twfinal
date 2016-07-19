package com.tw;

import com.tw.model.PayItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class PosServiceTest {

    @Test
    public void testNoPromotionOutput(){
        PosService posService = new PosService();

        List<PayItem> payItems = new ArrayList<PayItem>();
        payItems.add(new PayItem("可口可乐","ITEM000001","瓶", 3.00, 3));
        payItems.add(new PayItem("羽毛球","ITEM000003","个", 1.00, 5));
        payItems.add(new PayItem("苹果","ITEM000005","斤", 5.50, 2));

        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
				+ "名称：可口可乐，数量：3.00瓶，单价：3.00\\(元\\)，小计：9.00\\(元\\)\n"
				+ "名称：羽毛球，数量：5.00个，单价：1.00\\(元\\)，小计：5.00\\(元\\)\n"
				+ "名称：苹果，数量：2.00斤，单价：5.50\\(元\\)，小计：11.00\\(元\\)\n"
				+ "-{20,50}\n"
				+ "总计：25.00\\(元\\)\n"
				+ "\\*{20,50}";

        String result = posService.getOutput(payItems);
        assertTrue(result.matches(expectResult));
    }

    @Test
    public void testPromotionBuyTwoGetOneFreeOutput(){
        PosService posService = new PosService();
        posService.getAllPromotions().add(new PromotionBuyTwoGetOneFree("ITEM000001,ITEM000003"));

        List<PayItem> payItems = new ArrayList<PayItem>();
        payItems.add(new PayItem("可口可乐","ITEM000001","瓶", 3.00, 3));
        payItems.add(new PayItem("羽毛球","ITEM000003","个", 1.00, 5));
        payItems.add(new PayItem("苹果","ITEM000005","斤", 5.50, 2));

        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
                + "名称：可口可乐，数量：3.00瓶，单价：3.00\\(元\\)，小计：6.00\\(元\\)\n"
                + "名称：羽毛球，数量：5.00个，单价：1.00\\(元\\)，小计：4.00\\(元\\)\n"
                + "名称：苹果，数量：2.00斤，单价：5.50\\(元\\)，小计：11.00\\(元\\)\n"
                + "-{20,50}\n"
                + "买二赠一商品:\n"
                + "名称：可口可乐,数量：1瓶\n"
                + "名称：羽毛球,数量：1个\n"
                + "-{20,50}\n"
                + "总计：21.00\\(元\\)\n"
                + "节省：4.00\\(元\\)\n"
                + "\\*{20,50}";

        String result = posService.getOutput(payItems);
        assertTrue(result.matches(expectResult));
    }

  /*  @Test
    public void testPromotionDiscountOutput(){
        PosService posService = new PosService();
        posService.getAllPromotions().add(new PromotionDiscount("ITEM000005"));

        List<PayItem> payItems = new ArrayList<PayItem>();
        payItems.add(new PayItem("可口可乐","ITEM000001","瓶", 3.00, 3));
        payItems.add(new PayItem("羽毛球","ITEM000003","个", 1.00, 5));
        payItems.add(new PayItem("苹果","ITEM000005","斤", 5.50, 2));

        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
                + "名称：可口可乐，数量：3.00瓶，单价：3.00\\(元\\)，小计：9.00\\(元\\)\n"
                + "名称：羽毛球，数量：5.00个，单价：1.00\\(元\\)，小计：5.00\\(元\\)\n"
                + "名称：苹果，数量：2.00斤，单价：5.50\\(元\\)，小计：10.45\\(元\\),节省：0.55\\(元\\)\n"
                + "-{20,50}\n"
                + "总计：24.45\\(元\\)\n"
                + "节省：0.55\\(元\\)\n"
                + "\\*{20,50}";

        String result = posService.getOutput(payItems);
        assertTrue(result.matches(expectResult));
    } */
}
