package com.tw.service.input;

import com.tw.model.PayItem;

import java.util.ArrayList;


public interface InputService {
    /**
     * 将输入的字符串转化成ArrayList<PayItem>，以供处理使用
     * @param barcodes
     * @return ArrayList<PayItem>
     */
    public ArrayList<PayItem> transferStringToList(String barcodes);
}
