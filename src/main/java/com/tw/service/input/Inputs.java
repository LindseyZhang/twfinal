package com.tw.service.input;

import com.tw.model.PayItem;

import java.util.ArrayList;

/**
 * Created by qq422 on 2016/7/17.
 */
public interface Inputs {
    public ArrayList<PayItem> getPayItems(String barcodes);
}
