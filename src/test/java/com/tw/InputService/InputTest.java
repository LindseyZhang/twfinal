package com.tw.InputService;

import com.tw.entity.Item;
import com.tw.model.PayItem;
import com.tw.service.ItemService;
import com.tw.service.input.InputService;
import com.tw.service.input.inputsImpl.InputServiceImple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InputTest.class)
public class InputTest {
    @Mock
    ItemService itemService;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testInputService() {
        String barcodes = "['ITEM000001','ITEM000001','ITEM000001','ITEM000001',"
                + "'ITEM000001','ITEM000003','ITEM000003', 'ITEM000005' ]";
        HashMap<String,Item> itemMap = new HashMap<>();
        itemMap.put("ITEM000001", new Item("ITEM000001", "可口可乐", "瓶", "", "", 3.00));
        itemMap.put("ITEM000003", new Item("ITEM000003", "羽毛球", "个", "", "", 1.00));
        itemMap.put("ITEM000005", new Item("ITEM000005", "苹果", "斤", "", "", 5.50));
        InputService inputs = new InputServiceImple();
        when(itemService.loadItemFromDBToMap()).thenReturn(itemMap);
        ((InputServiceImple)inputs).itemService = itemService;
        ArrayList<PayItem> result = inputs.transferStringToList(barcodes);
        assertEquals(3,result.size());
    }
}
