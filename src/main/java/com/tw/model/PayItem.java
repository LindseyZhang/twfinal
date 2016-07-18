package com.tw.model;

import com.tw.entity.Item;

public class PayItem {
    private static final double DEFAULT_COUNT = 1;

    private String name;
    private String barcode;
    private String unit;
    private double price;
    private double count;

    public PayItem(String name, String barcode, String unit, double price, double count) {
        this.name = name;
        this.barcode = barcode;
        this.unit = unit;
        this.price = price;
        this.count = count;
    }

    public PayItem(Item item){
        this.name = item.getName();
        this.barcode = item.getBarcode();
        this.unit = item.getUnit();
        this.price = item.getPrice();
        this.count = DEFAULT_COUNT;
    }

    @Override
    public String toString() {
        return "名称：" + this.getName()
                + "，数量：" + this.getCount() + this.getUnit()
                + "，单价：" + String.format("%.2f", this.getPrice())
                + "(元)";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
