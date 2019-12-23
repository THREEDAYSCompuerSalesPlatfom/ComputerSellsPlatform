package com.threeDays.Utils.SortUtils;

import java.math.BigInteger;
import java.util.Map;

public class BigintegerSort extends  AbstractSort<Map.Entry<BigInteger,Integer>> {
    @Override
    protected boolean compare(Map.Entry<BigInteger,Integer> o1, Map.Entry<BigInteger,Integer> o2) {
       int i= o1.getValue().compareTo(o2.getValue());
        if(i<0){
            return true;
        }else {
            return false;
        }
    }
}
