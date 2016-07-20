package com.tw.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by qq422 on 2016/7/19.
 */
public class ComputedItem {

    //是否有促销，若有则放入该链表中，该链表对应了promotions图中的键值
    private ArrayList<String> promotianName;
    //普通商品链表
    private ArrayList<PayItem> normalPayitem;
    //按促销方式不同分类的链表，其中分别放了有促销名相对应的商品的链表
    private HashMap<String,ArrayList<PromotedItem>> promotions;
    //总金额
    private double totalPrice;
    //共节省金额
    private double discountPrice;

    public ComputedItem(){
        promotianName = new ArrayList<>();
        normalPayitem = new ArrayList<>();
        promotions = new HashMap<>();
    }

    public ArrayList<String> getPromotianName() {
        return promotianName;
    }

    public void setPromotianName(ArrayList<String> promotianName) {
        this.promotianName = promotianName;
    }

    public ArrayList<PayItem> getNormalPayitem() {
        return normalPayitem;
    }

    public void setNormalPayitem(ArrayList<PayItem> normalPayitem) {
        this.normalPayitem = normalPayitem;
    }

    public HashMap<String, ArrayList<PromotedItem>> getPromotions() {
        return promotions;
    }

    public void setPromotions(HashMap<String, ArrayList<PromotedItem>> promotions) {
        this.promotions = promotions;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputedItem that = (ComputedItem) o;

        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        if (Double.compare(that.discountPrice, discountPrice) != 0) return false;
        if (promotianName != null ? !promotianName.equals(that.promotianName) : that.promotianName != null)
            return false;
        if (normalPayitem != null ? !normalPayitem.equals(that.normalPayitem) : that.normalPayitem != null)
            return false;
        return promotions != null ? promotions.equals(that.promotions) : that.promotions == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = promotianName != null ? promotianName.hashCode() : 0;
        result = 31 * result + (normalPayitem != null ? normalPayitem.hashCode() : 0);
        result = 31 * result + (promotions != null ? promotions.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(discountPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
