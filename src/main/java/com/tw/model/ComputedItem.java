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
}
