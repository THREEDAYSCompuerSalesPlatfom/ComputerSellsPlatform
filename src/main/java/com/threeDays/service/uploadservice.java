package com.threeDays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

@Service
public class uploadservice {
    String parentDir = "D:" + File.separator + File.separator + "goods" + File.separator;//图片储存的目录
    @Autowired
    private BigGoodsService bigGoodsService;

    /**
     * 上传首页展示图(这方法用一次过后再用功能为更新首页图)
     */
    public String uploadindex(@RequestParam("goodsid") BigInteger goods_id,
                              @RequestParam("file") MultipartFile file) {
        if(goods_id==null){
            return "不存在此商品";
        }
        if(bigGoodsService.getBigGoods(goods_id)==null)
            return "不存在此商品";

        //1. 接受上传的文件  @RequestParam("file") MultipartFile file
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            //同时辅以sellerid为文件名，以&分割
            String fileName = "index";
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = parentDir + goods_id + File.separator + fileName;
            System.out.println(destFileName);
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用
          //  m.addAttribute("fileName", fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }

        return "success";
    }


}
