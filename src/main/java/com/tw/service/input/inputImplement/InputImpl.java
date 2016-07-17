package com.tw.service.input.inputImplement;

import com.tw.entity.Item;
import com.tw.model.PayItem;
import com.tw.service.ItemService;
import com.tw.service.input.Inputs;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * Created by qq422 on 2016/7/17.
 */
public class InputImpl implements Inputs {

    public HashMap<String, Item> itemMap;

    @Autowired
    public ItemService itemService;

    /*
	 * 正则表达式来确认输入字符的合法性
	 */
    Pattern pattern = Pattern.compile("^ITEM[0-9]{6}|^ITEM[0-9]{6}-[0-9]{1,}$");

    @Override
    public ArrayList<PayItem> getPayItems(String barcodes) {
        JSONArray inputsJson = new JSONArray(barcodes);
        if (inputsJson.length() == 0) {
            throw new IllegalArgumentException("there is no barcodes");
        }
        return mergeSameItems(getPayItems(inputsJson));
    }

    private ArrayList<PayItem> getPayItems(JSONArray inputsJson) {
        ArrayList<PayItem> payItems = new ArrayList<>();
        itemMap = itemService.getItemMap();

        for (Object input : inputsJson) {
            if (isCountProvided(input)) {
                payItems.add(getItemIfCountProvided(input));
            } else {
                payItems.add(convertToPayItem(itemMap.get(input), 1));
            }
        }
        return payItems;
    }
    private ArrayList<PayItem> mergeSameItems(ArrayList<PayItem> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("there is no pay item to show!");
        }

        ArrayList<PayItem> payItems = new ArrayList<>();

        for (PayItem item : items) {
            if (itemIsExist(item, payItems)) {
                updatePayItems(item, payItems);
            } else {
                payItems.add(item);
            }
        }

        return payItems;
    }
    private boolean itemIsExist(PayItem payItem, ArrayList<PayItem> payItems) {
        for (PayItem item : payItems) {
            if (item.getBarcode().equals(payItem.getBarcode())) {
                return true;
            }
        }
        return false;
    }

    private void updatePayItems(PayItem payItem, ArrayList<PayItem> payItems) {
        for (PayItem payItem1 : payItems) {
            if (payItem1.getBarcode().equals(payItem.getBarcode())) {
                payItem1.setCount(payItem1.getCount() + payItem.getCount());
            }
        }
    }

    private boolean isCountProvided(Object input) {
        return input.toString().contains("-");
    }

    private PayItem getItemIfCountProvided(Object input) {
        String[] barcodeCount = input.toString().split("-");
        return convertToPayItem(itemMap.get(barcodeCount[0]), getCount(barcodeCount));
    }

    private int getCount(String[] barcodeCount) {
        int count = parseInt(barcodeCount[1]);
        if (count < 0) {
            throw new IllegalArgumentException(barcodeCount[0] + "'s count is "
                    + barcodeCount[1] + ", can't be negative");
        }
        return count;
    }
    private PayItem convertToPayItem(Item item, int count) {
        return new PayItem(item.getName(), item.getBarcode(), item.getUnit(), item.getPrice(), count);
    }

}
