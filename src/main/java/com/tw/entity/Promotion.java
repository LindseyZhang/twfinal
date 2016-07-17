package com.tw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by qq422 on 2016/7/17.
 */

@Entity
@Table(name = "PROMOTION")
public class Promotion{

    @Id
    @Column(name = "PROMOTIONNAME")
    private String promotionName;

    @Column(name = "BARCODES")
    private String barcodes;

        public Promotion(String promotionName, String barcodes) {
            this.promotionName = promotionName;
            this.barcodes = barcodes;
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
}
