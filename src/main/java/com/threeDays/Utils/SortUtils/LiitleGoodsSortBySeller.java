package com.threeDays.Utils.SortUtils;

import com.threeDays.service.LittleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
@Service
public class LiitleGoodsSortBySeller extends AbstractSort<BigInteger> {
@Autowired
    private LittleGoodsService littleGoodsService;
    @Override
    protected boolean compare(BigInteger o1, BigInteger o2) {
        BigInteger sellero1=littleGoodsService.findSellerById(o1);
        BigInteger sellero2=littleGoodsService.findSellerById(o2);
        int i=sellero1.compareTo(sellero2);
        if(i==0 ||i==1){
            return true;
        }else {
            return false;
        }
    }
}
