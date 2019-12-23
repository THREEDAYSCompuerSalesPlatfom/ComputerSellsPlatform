package com.threeDays.service;

import com.hankcs.hanlp.HanLP;
import com.threeDays.Utils.SortUtils.AbstractSort;
import com.threeDays.Utils.SortUtils.BigintegerSort;
import com.threeDays.dao.BigGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

/**
 * 牛逼的模糊搜索算法
 * 自研
 * 1.借助https://github.com/hankcs/HanLP的分词工具先给查询的字段分词
 * 2.分出来的词依次搜索
 * 3.搜索完统计每此搜索符合关键词的商品id频率
 * 4.按频率从大到小输出
 * */
@Service
public class SearchService {

    private  final String[] computer = {""};//一开始可以剔除的关键字
    @Autowired
    private BigGoodsMapper bigGoodsMapper;

    // Map<Integer, BigInteger[]> AllListmap = new HashMap<>();//所有关键字所对biginteger数组存入alllistmap
    ArrayList<BigInteger> AllList = new ArrayList();//放所有查到的biggoodsid
    Map<BigInteger, Integer> map = new HashMap<>();//存入AllList中所有id的频率

    public List<BigInteger> search(String name) {
        // int index = 0;//map中的key，标识每次关键字查询所对数组
        out:
        for (Object term : (HanLP.segment(name)).toArray()) {//分词
            String text = term.toString();
            text = text.substring(0, text.indexOf("/"));
            for (String string : computer) {
                if (string.equals(text))
                    continue out;
            }

            searchforeach(text, AllList);
            // index++;
            //  System.out.println(text);


        }
        anylizeFrequency( AllList);
        return output();
    }


    private void searchforeach(String text, ArrayList<BigInteger> allList) {//查找关键字所对biginteger数组存入alllistmap中
        text="%"+text+"%";
        List<BigInteger> bigIntegers = bigGoodsMapper.searchGoods(text);
        // new BigintegerSort().QuickSort(bigIntegers, 0, bigIntegers.length);//排序,从小到大
        allList.addAll(allList.size()==0? 0:allList.size()-1, bigIntegers);
    }

    private void anylizeFrequency(List<BigInteger> alllist) {//查id出现的频率

        for (BigInteger str : alllist) {
            Integer num = map.get(str);
            map.put(str, num == null ? 1 : num + 1);
        }
    }

    private List<BigInteger> output() {
        List<Map.Entry<BigInteger, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<BigInteger, Integer>>() {//排序list，大在前
            public int compare(Map.Entry<BigInteger, Integer> o1,
                               Map.Entry<BigInteger, Integer> o2) {
                return -(o1.getValue().compareTo(o2.getValue()));
            }
        });
        double weight=avgWeight(list);//截断低于平均权重的结果
        ArrayList<BigInteger> arrayList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getValue()<weight)//排除低于平均权重的结果
                break;
            arrayList.add(i,list.get(i).getKey());
        }
        AllList.clear();//清空防止下次搜索出现上次结果
        map.clear();//清空防止下次搜索出现上次结果
        return arrayList;
    }

    private double avgWeight(List<Map.Entry<BigInteger, Integer>> list){//获取平均权重
        int sumweight=0;
        for(Map.Entry<BigInteger, Integer> map:list){
            System.out.println("商品id："+map.getKey()+"  权重："+map.getValue());
            sumweight=sumweight+map.getValue();
        }
       // int avgweight=sumweight/list.size();
        Double wei=(double) sumweight/list.size();
        System.out.println("平均权重："+wei);


        return wei;
    }

}

