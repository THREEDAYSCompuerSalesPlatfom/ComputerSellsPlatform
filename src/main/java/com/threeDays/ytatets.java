package com.threeDays;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.io.IOException;
import java.util.List;

public class ytatets {
    public static void main(String[] args) throws IOException {

    String ltext="联想U盘";
      // (HanLP.segment("你好，欢迎使用HanLP汉语处理包！")).toArray();
        for(Object term:(HanLP.segment(ltext)).toArray()){
            String text=term.toString();
            text=text.substring(0,text.indexOf("/"));
            System.out.println(text);
        }

      //  System.out.println((HanLP.segment("你好，欢迎使用HanLP汉语处理包！")).toArray()[1]);
       // SpeedTokenizer.segment("").toArray()[0];


    }

    public void searchforeach(String text){
      //  aa.substring(0, aa.indexOf(","));
        text=text.substring(0,text.indexOf("/"));
    }
}
