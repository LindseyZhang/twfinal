package com.tw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PROMOTION")
public class Promotion {

    @Id
    @Column(name = "PROMOTIONNAME")
    private String promotionName;

    @Column(name = "BARCODES")
    private String barcodes;

    @Column(name = "LEVEL")
    private int level;


    public Promotion(String promotionName, String barcodes, int level) {
        this.promotionName = promotionName;
        this.barcodes = barcodes;
        this.level = level;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(String barcodes) {
        this.barcodes = barcodes;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
