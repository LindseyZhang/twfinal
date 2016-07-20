package com.tw;

import com.tw.entity.Item;
import com.tw.entity.Promotion;
import com.tw.model.ComputedItem;
import com.tw.model.PayItem;
import com.tw.service.ItemService;
import com.tw.service.compute.ComputeService;
import com.tw.service.compute.computeImpl.ComputeServiceImpl;
import com.tw.service.input.InputService;
import com.tw.service.input.inputsImpl.InputServiceImple;
import com.tw.service.ouputService.OutputService;
import com.tw.service.ouputService.outputImpl.OutputServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TwFinalApplicationTests.class)
public class TwFinalApplicationTests {

    @Mock
    ItemService itemService;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testTwFinalApplicationTests() {
        String barcodes = "['ITEM000001', 'ITEM000001',  'ITEM000001',  'ITEM000001',  'ITEM000001',  'ITEM000003-2',  'ITEM000005',  'ITEM000005',  'ITEM000005']";
        HashMap<String,Item> itemMap = new HashMap<>();
        itemMap.put("ITEM000001", new Item("ITEM000001", "可口可乐", "瓶", "", "", 3.00));
        itemMap.put("ITEM000003", new Item("ITEM000003", "羽毛球", "个", "", "", 1.00));
        itemMap.put("ITEM000005", new Item("ITEM000005", "苹果", "斤", "", "", 5.50));
        InputService inputs = new InputServiceImple();
        when(itemService.loadItemFromDBToMap()).thenReturn(itemMap);
        ((InputServiceImple)inputs).itemService = itemService;
        ArrayList<PayItem> result = inputs.transferStringToList(barcodes);
        List<Promotion> promotions = new ArrayList<>();
        promotions.add(new Promotion("BUY_TWO_GET_ONE_FREE","ITEM000001,ITEM000003",1));
        promotions.add(new Promotion("SELL_BY_95","ITEM000005",2));
        ComputeService computeService = new ComputeServiceImpl();
        when(itemService.loadPromotionFromDB()).thenReturn(promotions);
        ((ComputeServiceImpl)computeService).itemService = itemService;
        ComputedItem computedItem = computeService.computePromotion(result);
        OutputService outputService = new OutputServiceImpl();
        String results = outputService.getOutput(computedItem);
        System.out.println(results);
        String expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
                + "名称：可口可乐，数量：7瓶，单价：3.00\\(元\\)，小计：15.00\\(元\\)\n"
                + "名称：羽毛球，数量：3个，单价：1.00\\(元\\)，小计：2.00\\(元\\)\n"
                + "名称：苹果，数量：3斤，单价：5.50\\(元\\)，小计：15.67\\(元\\)，节省：0.83\\(元\\)\n"
                + "-{20,50}\n"
                + "买二赠一商品：\n"
                + "名称：可口可乐，数量：2瓶\n"
                + "名称：羽毛球，数量：1个\n"
                + "-{20,50}\n"
                + "总计：32.68\\(元\\)\n"
                + "节省：7.83\\(元\\)\n"
                + "\\*{20,50}\n";
        assertTrue(results.matches(expectResult));
    }
}
