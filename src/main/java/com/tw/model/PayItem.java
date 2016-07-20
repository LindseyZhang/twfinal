package com.tw.model;

import com.tw.entity.Item;

public class PayItem {

    private String name;
    private String barcode;
    private String unit;
    private double price;
    private int count;

    public PayItem(String name, String barcode, String unit, double price, int count) {
        this.name = name;
        this.barcode = barcode;
        this.unit = unit;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "名称：" + this.getName()
                + "，数量：" + this.getCount() + this.getUnit()
                + "，单价：" + String.format("%.2f", this.getPrice())
                + "(元)"
                +"，小计：" + String.format("%.2f", this.getPrice()*this.count)+"(元)\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayItem payItem = (PayItem) o;

        if (Double.compare(payItem.price, price) != 0) return false;
        if (count != payItem.count) return false;
        if (name != null ? !name.equals(payItem.name) : payItem.name != null) return false;
        if (barcode != null ? !barcode.equals(payItem.barcode) : payItem.barcode != null) return false;
        return unit != null ? unit.equals(payItem.unit) : payItem.unit == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + count;
        return result;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
