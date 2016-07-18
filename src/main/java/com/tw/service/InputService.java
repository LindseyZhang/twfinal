package com.tw.service;

import com.tw.entity.Item;
import com.tw.model.PayItem;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputService {
    static Logger logger = Logger.getLogger (InputService.class.getName());

    public HashMap<String, Item> itemMap;

    @Autowired
    public ItemService itemService;

    /*
	 * 正则表达式来确认输入字符的合法性
	 */
    static Pattern pattern = Pattern.compile("^ITEM[0-9]{6}|^ITEM[0-9]{6}-[0-9]{1,}$");


    public InputService() {
   //     itemMap = itemService.loadItemToMap();
    }

    public List<PayItem> getPayItems(String barcodes) {
        logger.info("user input barcodes :" + barcodes);
        itemMap = itemService.loadItemToMap();  //TODO
        ArrayList<PayItem> payItemList = new ArrayList<>();
        for (PayItem item : getFinalPayItemMap(barcodes).values()) {
            payItemList.add(item);
        }
        logger.info("transfer barcodes String to ArrayList<PayItem> success");
        return payItemList;
    }

    private HashMap<String, PayItem> getFinalPayItemMap(String barcodes) {
        HashMap<String, PayItem> payItemMap = new HashMap<>();
        JSONArray barcodesArray = new JSONArray(barcodes);
        for (Object barcodeCountForamt : barcodesArray) {
            checkBarcodeFormat((String)barcodeCountForamt);
            mergeCurrentBarcodeCountFormatToPayItemMap(payItemMap, (String) barcodeCountForamt);
        }
        return payItemMap;
    }
    private void checkBarcodeFormat(String inBarcode) {
        Matcher matcher = pattern.matcher(inBarcode);
        if(!matcher.matches()){
            logger.error("wrong barcode format:"+inBarcode);
            throw new IllegalArgumentException("wrong barcode format:"+inBarcode);
        }
    }

    private void mergeCurrentBarcodeCountFormatToPayItemMap(Map<String, PayItem> payItemMap, String barcodeCountFormat) {
        String[] spiltString = barcodeCountFormat.split("-");
        if(payItemMap.containsKey(spiltString[0])){
            PayItem item = payItemMap.get(spiltString[0]);
            if(spiltString.length==1){
                payItemMap.get(spiltString[0]).setCount(item.getCount()+1);
            }else{
                payItemMap.get(spiltString[0]).setCount(item.getCount()+Integer.parseInt(spiltString[1]));
            }
        }else{
            Item item = itemMap.get(spiltString[0]);
            PayItem payItem = new PayItem(item);
            if(spiltString.length != 1){
                payItem.setCount(Integer.parseInt(spiltString[1]));
            }
            payItemMap.put(payItem.getBarcode(), payItem);
        }
    }
}
