package com.threeDays.controller.commentController;

import com.threeDays.POJO.cuOrderGoods;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.math.BigInteger;

/**
 * @ClassNamecommentController
 * @Date2019-12-294:23
 **/
@Controller
public class commentController {
    @RequestMapping("/orderDetail")
    public String orderDetail(@RequestParam("cuOrderGoods") cuOrderGoods cuOrderGoods, Model model) {
        model.addAttribute("cuOrderGoods", cuOrderGoods);

        return "orderDetail";
    }

    @RequestMapping("/cuComment")
    public String cuComment(@RequestParam("cuOrderGoods") cuOrderGoods cuOrderGoods, Model model) {
        model.addAttribute("cuOrderGoods", cuOrderGoods);
        return "cu-comment";
    }

    @RequestMapping("/cuBack")
    public String cuBack(@RequestParam("cuOrderGoods") cuOrderGoods cuOrderGoods, Model model) {
        model.addAttribute("cuOrderGoods", cuOrderGoods);
        return "cu-back";
    }

    @RequestMapping("/change")
    public String comment(@RequestParam("comment") String comment,
                          @RequestParam("orderId") BigInteger orderId,
                          @RequestParam("file")String file) {
        String parentDir = "D:" + File.separator + File.separator + file + File.separator;//文本储存的目录
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try {
            String fileName = "comment";
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = parentDir + orderId + File.separator + comment;
            File destFile = new File(destFileName);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            bufferedReader = new BufferedReader(new StringReader(comment));
            bufferedWriter = new BufferedWriter(new FileWriter(destFile));
            char buf[] = new char[1024];         //字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();

            return "fail";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }
}
