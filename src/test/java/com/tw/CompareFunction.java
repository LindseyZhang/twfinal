package com.tw;

import com.tw.service.Promotion.PromotionBase;

import java.util.List;

public class CompareFunction<T> {
    public boolean TwoListIsEqual(List<T> result, List<T> expectedResult) {
        if (result==expectedResult) {
            return true;
        }

        if (result==null || expectedResult==null) {
            return false;
        }

        int size = result.size();
        if (expectedResult.size() != size) {
            return false;
        }

        for(int i = 0; i < size; ++i) {
            if (!expectedResult.get(i).equals(result.get(i))) {
                return false;
            }
        }

        return true;
    }
}
