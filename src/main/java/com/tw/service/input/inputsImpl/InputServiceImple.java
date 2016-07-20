package com.tw.service.input.inputsImpl;

import com.tw.entity.Item;
import com.tw.model.PayItem;
import com.tw.service.ItemService;
import com.tw.service.input.InputService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class InputServiceImple implements InputService {
    static Logger logger = Logger.getLogger (InputServiceImple.class.getName());

    public HashMap<String, Item> itemMap;

    @Autowired
    public ItemService itemService;

    /*
	 * 正则表达式来确认输入字符的合法性
	 */
    Pattern pattern = Pattern.compile("^ITEM[0-9]{6}|^ITEM[0-9]{6}-[0-9]{1,}$");

    @Override
    public ArrayList<PayItem> transferStringToList(String barcodes) {
        logger.info("user input barcodes :" + barcodes);
        itemMap = itemService.loadItemFromDBToMap();
        return transferMapToList(barcodes);
    }

    private ArrayList<PayItem> transferMapToList(String barcodes){
        ArrayList<PayItem> payItemList = new ArrayList<>();
        for (PayItem item :  getPayItemHashMap(barcodes).values()) {
            payItemList.add(item);
        }
        logger.info("transfer barcodes String to ArrayList<PayItem> success");
        return payItemList;
    }

    private HashMap<String, PayItem> getPayItemHashMap(String barcodes) {
        HashMap<String, PayItem> payItemMap = new HashMap<>();
        JSONArray jsonArray = new JSONArray(barcodes);
        for (Object object : jsonArray) {
            checkBarcodeFormat((String)object);
            String[] spiltString = ((String)object).split("-");
            checkInStore(payItemMap, spiltString[0]);
            addEachPayItemIntoMap(payItemMap, spiltString);
        }
        return payItemMap;
    }

    private void checkInStore(HashMap<String, PayItem> payItemMap, String object) {
        if(!itemMap.containsKey(object))
            throw new RuntimeException(object +" is not in store");
    }

    private void checkBarcodeFormat(String inBarcode) {
        Matcher matcher = pattern.matcher(inBarcode);
        if(!matcher.matches()){
            logger.error("wrong barcode format:"+inBarcode);
            throw new IllegalArgumentException("wrong barcode format:"+inBarcode);
        }
    }

    private void addEachPayItemIntoMap(HashMap<String, PayItem> payItemMap, String[] spiltString) {
        if(payItemMap.containsKey(spiltString[0])){
            //若map中已经有该商品了，则添加
            PayItem item = payItemMap.get(spiltString[0]);
            if(spiltString.length==1){
                payItemMap.get(spiltString[0]).setCount(item.getCount()+1);
            }else{
                payItemMap.get(spiltString[0]).setCount(item.getCount()+Integer.parseInt(spiltString[1]));
            }
        }else{
            //若map中还没有该商品，则new PayItem，并对其赋值
            Item item = itemMap.get(spiltString[0]);
            PayItem payItem = new PayItem(item.getName(), item.getBarcode(), item.getUnit(), item.getPrice(), 0);
            if(spiltString.length==1){
                payItem.setCount(1);
            }else{
                payItem.setCount(Integer.parseInt(spiltString[1]));
            }
            payItemMap.put(payItem.getBarcode(), payItem);
        }
    }

}
