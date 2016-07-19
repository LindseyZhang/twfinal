package com.tw;

import com.tw.entity.Item;
import com.tw.model.PayItem;
import com.tw.service.ItemService;
//import com.tw.service.OutputService;
//import com.tw.service.PosService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TwFinalApplicationTests {
//	private PosService posService;
//
//	@Mock
//	ItemService itemService;
//
//	private HashMap<String, Item> itemMap;
//	private String expectResult = "";
//
//	@Before
//	public void setUp() throws Exception {
//		initMocks(this);
////		posService = new PosService();
////		posService.itemService = itemService;
////		posService.setOutputService(new OutputService());
//
//		expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
//				+ "名称：可口可乐，数量：5瓶，单价：3.00\\(元\\)，小计：15.00\\(元\\)\n"
//				+ "名称：雪碧，数量：2瓶，单价：3.00\\(元\\)，小计：6.00\\(元\\)\n"
//				+ "名称：电池，数量：1个，单价：2.00\\(元\\)，小计：2.00\\(元\\)\n"
//				+ "-{20,50}\n"
//				+ "总计：23.00\\(元\\)\n"
//				+ "\\*{20,50}";

////		posService.itemMap = itemMap;
//	}
//	@Before
//	public void setUp() throws Exception {
//		initMocks(this);
//	}
//	@Test
//	public void testGenerateReceiptFromBarcodes() {
//		String barcodes = "['ITEM000000','ITEM000000','ITEM000000','ITEM000000',"
//				+ "'ITEM000000','ITEM000001','ITEM000001', 'ITEM000004' ]";
//		HashMap<String,Item> itemMap = new HashMap<>();
//		itemMap.put("ITEM000000", new Item("ITEM000000", "可口可乐", "瓶", "", "", 3.00));
//		itemMap.put("ITEM000001", new Item("ITEM000001", "雪碧", "瓶", "", "", 3.00));
//		itemMap.put("ITEM000004", new Item("ITEM000004", "电池", "个", "", "", 2.00));
//		Inputs inputs = new InputImpl();
//		when(itemService.loadItemFromDBToMap()).thenReturn(itemMap);
//		((InputImpl)inputs).itemService = itemService;
//		ArrayList<PayItem> result = inputs.getPayItems(barcodes);
//		assertEquals(result.size(),3);
//	}
//
//	@Test
//	public void testGenerateReceiptFromBarcodesWithRepeat() {
//		String barcodes = "['ITEM000000-3','ITEM000000-2','ITEM000001-2', 'ITEM000004']";
//		when(itemService.loadItemFromDBToMap()).thenReturn(itemMap);
//
//		String result = posService.generateReceiptFromBarcodes(barcodes);
//
//		assertTrue(result.matches(expectResult));
//	}
//
//	@Test
//	public void testGenerateReceiptPromotionByType() {
//		String barcodes = "['ITEM000000-10','ITEM000000-2','ITEM000001-2', 'ITEM000004']";
//		posService.setPromotionInfo("[{type: 'SELL_BY_TYPE',barcodes: 'ITEM000000'}]");
//
//		expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
//				+ "名称：可口可乐，数量：12瓶，单价：3.00\\(元\\)，小计：34.20\\(元\\),优惠：1.80\\(元\\)\n"
//				+ "名称：雪碧，数量：2瓶，单价：3.00\\(元\\)，小计：6.00\\(元\\)\n"
//				+ "名称：电池，数量：1个，单价：2.00\\(元\\)，小计：2.00\\(元\\)\n"
//				+ "-{20,50}\n"
//				+ "批发价出售商品\n"
//				+ "名称：可口可乐，数量：12瓶\n"
//				+ "-{20,50}\n"
//				+ "总计：42.20\\(元\\)\n"
//				+ "节省：1.80\\(元\\)\n"
//				+ "\\*{20,50}";
//		when(itemService.loadItemFromDBToMap()).thenReturn(itemMap);
//
//		String result = posService.generateReceiptFromBarcodes(barcodes);
//
//		assertTrue(result.matches(expectResult));
//	}
//
//	@Test
//	public void testGenerateReceiptPromotionByBuyThreeFreeOne() {
//		String barcodes = "['ITEM000000-10','ITEM000000-2','ITEM000001-2', 'ITEM000004']";
//		posService.setPromotionInfo("[{type: 'BUY_THREE_GET_ONE_FREE',barcodes: 'ITEM000000'}]");
//
//		expectResult = "\\*\\*\\*<没钱赚商店>购物清单\\*\\*\\*\n"
//				+ "名称：可口可乐，数量：12瓶，单价：3.00\\(元\\)，小计：36.00\\(元\\)\n"
//				+ "名称：雪碧，数量：2瓶，单价：3.00\\(元\\)，小计：6.00\\(元\\)\n"
//				+ "名称：电池，数量：1个，单价：2.00\\(元\\)，小计：2.00\\(元\\)\n"
//				+ "-{20,50}\n"
//				+ "买三免一商品：\n"
//				+ "名称：可口可乐，数量：4瓶\n"
//				+ "-{20,50}\n"
//				+ "总计：32.00\\(元\\)\n"
//				+ "节省：12.00\\(元\\)\n"
//				+ "\\*{20,50}";
//		when(itemService.loadItemFromDBToMap()).thenReturn(itemMap);
//
//		String result = posService.generateReceiptFromBarcodes(barcodes);
//
//		assertTrue(result.matches(expectResult));
//	}

}
