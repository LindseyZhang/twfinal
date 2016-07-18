package com.tw.service.Promotion;

import com.tw.model.PayItem;
import com.tw.service.CompareFunction;

import java.util.ArrayList;
import java.util.List;

public class PromotionBase {
    private List<String> barcodes;

    private CompareFunction compareFunction = new CompareFunction();

    public PromotionBase(String barcodes){
        LoadPromotionBarcodes(barcodes);
    }

    public boolean isValidPromotionItem(PayItem item) {
        for(String barcode: barcodes){
            if (item.getBarcode().equals(barcode)){
                return true;
            }
        }
        return false;
    }

    public double getMoneySavedByCurrentItem(PayItem item) {
        return 0.00;
    }

    public String getItemSellDetail(PayItem item) {
        return null;
    }

    public String getPromotionName() {
        return null;
    }

    public String getItemPromotionDetail(PayItem item) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionBase that = (PromotionBase) o;

        if (!compareFunction.TwoListIsEqual(that.barcodes,this.barcodes)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return barcodes.hashCode();
    }

    private void LoadPromotionBarcodes(String barcodesString) {
        barcodes = new ArrayList<String>();
        String[] splitBarcodesString = barcodesString.split(",");
        for (String barcode: splitBarcodesString){
            barcodes.add(barcode);
        }
    }


}
